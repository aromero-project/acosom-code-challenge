package com.aromero.flink;

import com.aromero.flink.model.UserDataCombine;
import com.aromero.flink.model.UserDataPresentation;
import com.aromero.flink.model.UserInformation;
import com.aromero.flink.model.UserInteraction;
import org.apache.flink.api.common.functions.JoinFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.ExecutionCheckpointingOptions;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.ProcessingTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

import java.time.LocalDateTime;
import java.util.HashMap;

import static com.aromero.flink.consumer.KafkaConsumers.createUserInformationConsumer;
import static com.aromero.flink.consumer.KafkaConsumers.createUserInteractionConsumer;
import static com.aromero.flink.consumer.KafkaProducers.createUserDataPresentationProducer;

public class DataAnalyticsStreamJob {

    private static final String USER_INFORMATION_TOPIC = "user.information.data";
    private static final String USER_INTERACTION_TOPIC = "user.interaction.data";
    private static final String USER_PRESENTATION_TOPIC = "user.presentation.data";
    private static final String BOOTSTRAP_SERVERS_VAL = "localhost:9092";
    private static final String GROUP_ID_VAL = "acosom-flink-consumer-group";

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);

        env.enableCheckpointing(5000); // start a checkpoint every 5000 ms

        env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE); // set mode to exactly-once (this is the default)
        env.getCheckpointConfig().setMinPauseBetweenCheckpoints(500); // make sure 500 ms of progress happen between checkpoints
        env.getCheckpointConfig().setCheckpointTimeout(60000); // checkpoints have to complete within one minute, or are discarded
        env.getCheckpointConfig().setTolerableCheckpointFailureNumber(2); // only two consecutive checkpoint failures are tolerated
        env.getCheckpointConfig().setMaxConcurrentCheckpoints(1); // allow only one checkpoint to be in progress at the same time

        // enable externalized checkpoints which are retained
        env.getCheckpointConfig().setExternalizedCheckpointCleanup(
                CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION); // after job cancellation
        env.getCheckpointConfig().enableUnalignedCheckpoints(); // enables the unaligned checkpoints
        // env.getCheckpointConfig().setCheckpointStorage("hdfs:///my/checkpoint/dir"); // sets the checkpoint storage where checkpoint snapshots will be written

        // enable checkpointing with finished tasks
        Configuration config = new Configuration();
        config.set(ExecutionCheckpointingOptions.ENABLE_CHECKPOINTS_AFTER_TASKS_FINISH, true);
        env.configure(config);


        FlinkKafkaConsumer<UserInformation> userInformationConsumer =
                createUserInformationConsumer(USER_INFORMATION_TOPIC, BOOTSTRAP_SERVERS_VAL, GROUP_ID_VAL);

        FlinkKafkaConsumer<UserInteraction> userInteractionConsumer =
                createUserInteractionConsumer(USER_INTERACTION_TOPIC, BOOTSTRAP_SERVERS_VAL, GROUP_ID_VAL);


        FlinkKafkaProducer<UserDataPresentation> userDataPresentationProducer =
                createUserDataPresentationProducer(USER_PRESENTATION_TOPIC, BOOTSTRAP_SERVERS_VAL);


        DataStream<UserInformation> userInformationDataStreamSource = env.addSource(userInformationConsumer);
        DataStream<UserInteraction> userInteractionDataStreamSource = env.addSource(userInteractionConsumer);


        userInformationDataStreamSource
                .join(userInteractionDataStreamSource)
                .where(userInformation -> userInformation.getUserId())
                .equalTo(userInteraction -> userInteraction.getUserId())
                .window(ProcessingTimeSessionWindows.withGap(Time.minutes(2)))
                .apply(new JoinFunction<UserInformation, UserInteraction, UserDataCombine>() {
                    @Override
                    public UserDataCombine join(UserInformation userInformation, UserInteraction userInteraction) throws Exception {
                        return new UserDataCombine(userInformation.getUserId(), userInformation.getRegion(), userInformation.getUserLevel(),
                                userInteraction.getCreatedAt(), userInteraction.getPageId(), userInteraction.getAction());
                    }
                })
                .map(new MapFunction<UserDataCombine, Tuple4<UserDataCombine, String, Integer, HashMap<String, Double>    >>() {
                    @Override
                    public Tuple4<UserDataCombine, String,  Integer, HashMap<String, Double> > map(UserDataCombine userDataCombine) throws Exception {
                        return new Tuple4<>(userDataCombine, userDataCombine.getRegion(), Integer.valueOf(1), null);
                    }
                })
                .keyBy(1)
                .reduce(new ReduceFunction<Tuple4<UserDataCombine, String, Integer, HashMap<String, Double> >>() {
                    @Override
                    public Tuple4<UserDataCombine, String, Integer, HashMap<String, Double>> reduce(Tuple4<UserDataCombine, String, Integer, HashMap<String, Double>> userDataCombine,
                                                                                                    Tuple4<UserDataCombine, String, Integer, HashMap<String, Double>> t1) throws Exception {
                        int actionCounter = userDataCombine.f2 + t1.f2;
                        return new Tuple4<>(userDataCombine.f0, "", actionCounter, null);
                    }
                })

                .map(new MapFunction<Tuple4<UserDataCombine, String, Integer, HashMap<String, Double>>, UserDataPresentation>() {
                    @Override
                    public UserDataPresentation map(Tuple4<UserDataCombine, String, Integer, HashMap<String, Double>> userDataCombine) throws Exception {
                        UserDataPresentation userDataPresentation = UserDataPresentation.Builder.builder()
                                .region(userDataCombine.f1)
                                .totalActions(new Double(userDataCombine.f2))
                                .createdAt(LocalDateTime.now())
                                .build();

                        return userDataPresentation;
                    }
                })
                //.print();
                .addSink(userDataPresentationProducer);
//
//        ConnectedStreams<UserInformation, UserInteraction> connect = userInformationDataStreamSource.connect(userInteractionDataStreamSource);
//        ConnectedStreams<UserInformation, UserInteraction> keyedBy = connect.keyBy(userInformation -> userInformation.getUserId(), userInteraction -> userInteraction.getUserId());
//        SingleOutputStreamOperator<UserDataPresentation> singleOutputStreamOperator = connect.process(new UserDataCombineFunction());
        // KeyBy  = Group
        // for average we can use reduce
        // for reduce it's mandatory the input an output are the same data type

        // The fold operation is the same as reduce but the input and output could be different
//        SingleOutputStreamOperator<UserDataPresentation> process = keyedBy.process(new UserDataCombineFunction());

        env.execute("DataAnalyticsStreamJob");
    }
}


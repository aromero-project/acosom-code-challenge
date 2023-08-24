package com.aromero.flink.consumer;

import com.aromero.flink.model.UserInformation;
import com.aromero.flink.model.UserInteraction;
import com.aromero.flink.schema.UserInformationDeserializationSchema;
import com.aromero.flink.schema.UserInteractionDeserializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class KafkaConsumers {

    private static final String BOOTSTRAP_SERVER = "bootstrap.servers";
    private static final String GROUP_ID = "group.id";

    public static FlinkKafkaConsumer<String> createStringConsumerForTopic(String topic, String kafkaAddress, String kafkaGroup) {
        Properties props = new Properties();
        props.setProperty(BOOTSTRAP_SERVER, kafkaAddress);
        props.setProperty(GROUP_ID, kafkaGroup);
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), props);
        return consumer;
    }

    public static FlinkKafkaConsumer<UserInformation> createUserInformationConsumer(String topic, String kafkaAddress, String kafkaGroup) {
        Properties properties = new Properties();
        properties.setProperty(BOOTSTRAP_SERVER, kafkaAddress);
        properties.setProperty(GROUP_ID, kafkaGroup);
        FlinkKafkaConsumer<UserInformation> consumer = new FlinkKafkaConsumer<UserInformation>(topic, new UserInformationDeserializationSchema(), properties);
        return consumer;
    }

    public static FlinkKafkaConsumer<UserInteraction> createUserInteractionConsumer(String topic, String kafkaAddress, String kafkaGroup) {
        Properties properties = new Properties();
        properties.setProperty(BOOTSTRAP_SERVER, kafkaAddress);
        properties.setProperty(GROUP_ID, kafkaGroup);
        FlinkKafkaConsumer<UserInteraction> consumer = new FlinkKafkaConsumer<UserInteraction>(topic, new UserInteractionDeserializationSchema(), properties);
        return consumer;
    }
}

package com.aromero.flink.consumer;

import com.aromero.flink.model.UserDataPresentation;
import com.aromero.flink.schema.UserDataPresentationSerializationSchema;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;

public class KafkaProducers {

    public static FlinkKafkaProducer<String> createStringProducer(String topic, String kafkaAddress) {
        return new FlinkKafkaProducer<>(kafkaAddress, topic, new SimpleStringSchema());
    }

    public static FlinkKafkaProducer<UserDataPresentation> createUserDataPresentationProducer(String topic, String kafkaAddress) {
        return new FlinkKafkaProducer<UserDataPresentation>(kafkaAddress, topic, new UserDataPresentationSerializationSchema());
    }
}

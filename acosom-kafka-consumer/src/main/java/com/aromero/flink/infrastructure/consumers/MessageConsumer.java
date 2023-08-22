package com.aromero.flink.infrastructure.consumers;


import com.aromero.flink.common.dto.UserDataPresentation;
import com.aromero.flink.common.dto.UserInformation;
import com.aromero.flink.common.dto.UserInteraction;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface MessageConsumer {
    void consume(@Payload UserInformation message, Acknowledgment ack);
    void consume(@Payload UserInteraction message, Acknowledgment ack);
    void consume(@Payload UserDataPresentation message, Acknowledgment ack);

}

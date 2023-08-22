package com.aromero.flink.infrastructure;

import com.aromero.flink.common.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class UserMessageProducer implements MessageProducer {

    private final Logger logger = Logger.getLogger(UserMessageProducer.class.getName());

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void produce(String topic, Message message) {
        this.kafkaTemplate.send(topic, message);
        logger.log(Level.INFO, MessageFormat.format("Message success userId - {0} topic - {1}.", message.getUserId(), topic));
    }
}

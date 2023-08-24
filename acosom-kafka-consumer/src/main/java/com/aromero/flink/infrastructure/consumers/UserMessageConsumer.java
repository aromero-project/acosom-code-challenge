package com.aromero.flink.infrastructure.consumers;


import com.aromero.flink.common.dto.UserDataPresentation;
import com.aromero.flink.common.dto.UserInformation;
import com.aromero.flink.common.dto.UserInteraction;
import com.aromero.flink.infrastructure.handlers.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class UserMessageConsumer implements MessageConsumer {

    private final Logger logger = Logger.getLogger(UserMessageConsumer.class.getName());

    @Autowired
    private MessageHandler messageHandler;

    private static final String USER_INFORMATION_TOPIC = "user.information.data";
    private static final String USER_INTERACTION_TOPIC = "user.interaction.data";
    private static final String USER_PRESENTATION_TOPIC = "user.presentation.data";
    private static final String USER_DATA_GROUP_ID = "acosomUserDataGroupId";

    @KafkaListener(topics = USER_INFORMATION_TOPIC, groupId = USER_DATA_GROUP_ID)
    @Override
    public void consume(UserInformation message, Acknowledgment ack) {
        this.messageHandler.on(message);
        ack.acknowledge();
        logger.log(Level.INFO, MessageFormat.format("UserInformation message successfully consumed userId - {0}", message.getUserId()));
    }

    @KafkaListener(topics = USER_INTERACTION_TOPIC, groupId = USER_DATA_GROUP_ID)
    @Override
    public void consume(UserInteraction message, Acknowledgment ack) {
        this.messageHandler.on(message);
        ack.acknowledge();
        logger.log(Level.INFO, MessageFormat.format("UserInteraction message successfully consumed userId - {0}", message.getUserId()));
    }

    //@KafkaListener(topics = USER_PRESENTATION_TOPIC, groupId = USER_DATA_GROUP_ID)
    @Override
    public void consume(UserDataPresentation message, Acknowledgment ack) {
        this.messageHandler.on(message);
        ack.acknowledge();
        logger.log(Level.INFO, MessageFormat.format("UserDataPresentation message successfully consumed userId - {0}", message.getUserId()));
    }
}

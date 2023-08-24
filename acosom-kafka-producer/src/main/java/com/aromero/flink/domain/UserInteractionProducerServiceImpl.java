package com.aromero.flink.domain;

import com.aromero.flink.common.dto.Message;
import com.aromero.flink.common.dto.UserInteraction;
import com.aromero.flink.infrastructure.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("userInteractionProducer")
public class UserInteractionProducerServiceImpl implements UserProducerService {

    private static final String USER_INTERACTION_TOPIC = "user.interaction.data";

    @Autowired
    private MessageProducer messageProducer;

    @Override
    public void execute(Message message) {
        UserInteraction userInteraction = (UserInteraction) message;
        userInteraction.setCreatedAt(LocalDateTime.now());
        this.messageProducer.produce(USER_INTERACTION_TOPIC, userInteraction);
    }

    @Override
    public void execute(List<Message> messages) {
        for (Message message : messages ) {
            UserInteraction userInteraction = (UserInteraction) message;
            userInteraction.setCreatedAt(LocalDateTime.now());
            this.messageProducer.produce(USER_INTERACTION_TOPIC, userInteraction);
        }
    }
}

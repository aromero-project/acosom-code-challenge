package com.aromero.flink.domain;

import com.aromero.flink.common.dto.Message;
import com.aromero.flink.infrastructure.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInformationProducer")
public class UserInformationProducerServiceImpl implements UserProducerService{

    private static final String USER_INFORMATION_TOPIC = "user.information.data";

    @Autowired
    private MessageProducer messageProducer;

    @Override
    public void execute(Message message) {
        this.messageProducer.produce(USER_INFORMATION_TOPIC, message);
    }
}

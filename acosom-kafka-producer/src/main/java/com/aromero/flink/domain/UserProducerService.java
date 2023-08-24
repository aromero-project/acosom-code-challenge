package com.aromero.flink.domain;

import com.aromero.flink.common.dto.Message;

import java.util.List;

public interface UserProducerService {

    void execute(Message message);

    void execute(List<Message> messages);

}

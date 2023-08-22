package com.aromero.flink.domain;

import com.aromero.flink.common.dto.Message;

public interface UserProducerService {

    void execute (Message message);
}

package com.aromero.flink.infrastructure;

import com.aromero.flink.common.dto.Message;

public interface MessageProducer {
    void produce(String topic, Message message);
}

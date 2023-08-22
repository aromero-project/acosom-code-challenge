package com.aromero.flink.infrastructure.handlers;


import com.aromero.flink.common.dto.UserDataPresentation;
import com.aromero.flink.common.dto.UserInformation;
import com.aromero.flink.common.dto.UserInteraction;

public interface MessageHandler {
    void on(UserInformation message);

    void on(UserInteraction message);

    void on(UserDataPresentation message);
}

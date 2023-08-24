package com.aromero.flink.infrastructure.handlers;


import com.aromero.flink.common.dto.UserDataPresentation;
import com.aromero.flink.common.dto.UserInformation;
import com.aromero.flink.common.dto.UserInteraction;
import com.aromero.flink.domain.entity.UserDataPresentationModel;
import com.aromero.flink.domain.entity.UserInformationModel;
import com.aromero.flink.domain.entity.UserInteractionModel;
import com.aromero.flink.domain.UserDataPresentationRepository;
import com.aromero.flink.domain.UserInformationRepository;
import com.aromero.flink.domain.UserInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserMessageHandler implements MessageHandler {

    private final Logger logger = Logger.getLogger(UserMessageHandler.class.getName());

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private UserInteractionRepository userInteractionRepository;

    @Autowired
    private UserDataPresentationRepository userDataPresentationRepository;

    @Override
    public void on(UserInformation message) {

        if (message instanceof UserInformation) {
            UserInformationModel informationModel = UserInformationModel.builder()
                    .id(UUID.randomUUID().toString())
                    .userId(message.getUserId())
                    .userLevel(message.getUserLevel())
                    .region(message.getRegion())
                    .build();
            this.userInformationRepository.save(informationModel);
            logger.log(Level.INFO, MessageFormat.format("UserInformationModel save successfully in DB userId - {0}", message.getUserId()));
        }

    }

    @Override
    public void on(UserInteraction message) {
        if (message instanceof UserInteraction) {
            UserInteractionModel informationModel = UserInteractionModel.builder()
                    .id(UUID.randomUUID().toString())
                    .userId(message.getUserId())
                    .action(message.getAction())
                    .createdAt(message.getCreatedAt())
                    .pageId(message.getPageId())
                    .build();
            this.userInteractionRepository.save(informationModel);
            logger.log(Level.INFO, MessageFormat.format("UserInteractionModel save successfully in DB userId - {0}", message.getUserId()));
        }
    }

    @Override
    public void on(UserDataPresentation message) {
        if (message instanceof UserDataPresentation) {
            UserDataPresentationModel informationModel = UserDataPresentationModel.builder()
                    .id(UUID.randomUUID().toString())
                    .region(message.getRegion())
                    .createdAt(message.getCreatedAt())
                    .totalActions(message.getTotalActions())
                    .build();
            this.userDataPresentationRepository.save(informationModel);
            logger.log(Level.INFO, MessageFormat.format("UserDataPresentationModel save successfully in DB userId - {0}", message.getUserId()));
        }
    }
}

package com.aromero.flink.api.controllers;


import com.aromero.flink.api.dto.BaseResponse;
import com.aromero.flink.api.dto.UserProducerResponse;
import com.aromero.flink.common.dto.Message;
import com.aromero.flink.common.dto.UserInteraction;
import com.aromero.flink.domain.UserProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/userInteraction")
public class UserInteractionController {

    @Autowired
    @Qualifier("userInteractionProducer")
    UserProducerService userProducerService;
    private final Logger logger = Logger.getLogger(UserInteractionController.class.getName());

    @PostMapping
    public ResponseEntity<BaseResponse> createUserInteraction(@RequestBody UserInteraction userInteraction) {
        String userId = userInteraction.getUserId();
        try {
            this.userProducerService.execute(userInteraction);
            return new ResponseEntity<>(new UserProducerResponse("UserInteraction request completed successfully!", userId), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = MessageFormat.format("Error while processing request UserInteraction for id - {0}.", userId);
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new UserProducerResponse(safeErrorMessage, userId), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("list")
    public ResponseEntity<BaseResponse> createUserInteraction(@RequestBody List<UserInteraction> userInteractionList) {
        List<Message> messages = userInteractionList.stream()
                .filter(obj -> obj instanceof UserInteraction)
                .map(obj -> (Message) obj)
                .collect(Collectors.toList());
        try {
            this.userProducerService.execute(messages);
            return new ResponseEntity<>(new UserProducerResponse("UserInteraction request completed successfully!", ""), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}.", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing request UserInteraction";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new UserProducerResponse(safeErrorMessage, ""), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

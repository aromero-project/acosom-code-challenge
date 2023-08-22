package com.aromero.flink.api.dto;

import lombok.Data;

@Data
public class UserProducerResponse extends BaseResponse {
    private String userId;

    public UserProducerResponse(String message, String userId) {
        super(message);
        this.userId = userId;
    }
}

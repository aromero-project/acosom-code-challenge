package com.aromero.flink.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDataPresentation extends Message {


    private String region;
    private Double totalActions;
    //private Map<String, Double> userLevelAverage;
    private LocalDateTime createdAt;
}

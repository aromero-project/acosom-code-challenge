package com.aromero.flink.common.dto;


import com.aromero.flink.common.dto.enums.Action;
import com.aromero.flink.common.dto.enums.UserLevel;
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
    private UserLevel userLevel;

    private LocalDateTime createdAt;
    private Integer pageId;
    private Action action;
}

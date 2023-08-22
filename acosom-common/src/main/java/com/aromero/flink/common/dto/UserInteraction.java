package com.aromero.flink.common.dto;

import com.aromero.flink.common.dto.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserInteraction extends Message {

    private LocalDateTime createdAt;
    private Integer pageId;
    private Action action;
}

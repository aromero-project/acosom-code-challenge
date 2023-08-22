package com.aromero.flink.domain.entity;

import com.aromero.flink.common.dto.enums.Action;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "userInteraction")
public class UserInteractionModel extends BaseEntity {
    @Id
    private String id;
    private String userId;
    private LocalDateTime createdAt;
    private Integer pageId;
    private Action action;
}

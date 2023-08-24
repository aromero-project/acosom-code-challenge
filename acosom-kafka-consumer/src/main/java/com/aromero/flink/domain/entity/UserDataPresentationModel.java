package com.aromero.flink.domain.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@Document(collection = "userDataPresentation")
public class UserDataPresentationModel extends BaseEntity {

    @Id
    private String id;
    private String region;
    private Double totalActions;
    private Map<String, Double> userLevelAverage;
    private LocalDateTime createdAt;
}

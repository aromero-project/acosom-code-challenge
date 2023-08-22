package com.aromero.flink.domain.entity;

import com.aromero.flink.common.dto.enums.UserLevel;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "userInformation")
public class UserInformationModel extends BaseEntity {

    @Id
    private String id;
    private String userId;
    private String region;
    private UserLevel userLevel;
}

package com.aromero.flink.common.dto;


import com.aromero.flink.common.dto.enums.UserLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInformation extends Message {

    private String region;
    private UserLevel userLevel;

}

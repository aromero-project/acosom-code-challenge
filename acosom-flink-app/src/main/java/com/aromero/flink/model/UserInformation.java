package com.aromero.flink.model;


import com.aromero.flink.model.enums.UserLevel;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Objects;

@JsonSerialize
public class UserInformation  {

    private String userId;
    private String region;
    private UserLevel userLevel;

    public UserInformation() {
    }

    public UserInformation(String userId, String region, UserLevel userLevel) {
        this.userId = userId;
        this.region = region;
        this.userLevel = userLevel;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel) {
        this.userLevel = userLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInformation that = (UserInformation) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(region, that.region) &&
                userLevel == that.userLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, region, userLevel);
    }

    @Override
    public String toString() {
        return "UserInformation{" +
                "userId='" + userId + '\'' +
                ", region='" + region + '\'' +
                ", userLevel=" + userLevel +
                '}';
    }
}

package com.aromero.flink.model;

import com.aromero.flink.model.enums.Action;
import com.aromero.flink.model.enums.UserLevel;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;


public class UserDataCombine {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("region")
    private String region;

    @JsonProperty("userLevel")
    private UserLevel userLevel;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("pageId")
    private Integer pageId;

    @JsonProperty("action")
    private Action action;

    public UserDataCombine() {
    }

    public UserDataCombine(String userId, String region, UserLevel userLevel,
                           LocalDateTime createdAt, Integer pageId, Action action) {
        this.userId = userId;
        this.region = region;
        this.userLevel = userLevel;
        this.createdAt = createdAt;
        this.pageId = pageId;
        this.action = action;
    }

    private UserDataCombine(Builder builder) {
        setUserId(builder.userId);
        setRegion(builder.region);
        setUserLevel(builder.userLevel);
        setCreatedAt(builder.createdAt);
        setPageId(builder.pageId);
        setAction(builder.action);
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDataCombine that = (UserDataCombine) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(region, that.region) &&
                userLevel == that.userLevel &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(pageId, that.pageId) && action == that.action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, region, userLevel, createdAt, pageId, action);
    }

    @Override
    public String toString() {
        return "UserDataPresentation{" +
                "userId='" + userId + '\'' +
                ", region='" + region + '\'' +
                ", userLevel=" + userLevel +
                ", createdAt=" + createdAt +
                ", pageId=" + pageId +
                ", action=" + action +
                '}';
    }

    public static final class Builder {
        private String userId;
        private String region;
        private UserLevel userLevel;
        private LocalDateTime createdAt;
        private Integer pageId;
        private Action action;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder region(String val) {
            region = val;
            return this;
        }

        public Builder userLevel(UserLevel val) {
            userLevel = val;
            return this;
        }

        public Builder createdAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder pageId(Integer val) {
            pageId = val;
            return this;
        }

        public Builder action(Action val) {
            action = val;
            return this;
        }

        public UserDataCombine build() {
            return new UserDataCombine(this);
        }
    }
}

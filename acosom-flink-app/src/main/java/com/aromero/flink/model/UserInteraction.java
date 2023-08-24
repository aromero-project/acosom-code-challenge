package com.aromero.flink.model;

import com.aromero.flink.model.enums.Action;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Objects;


@JsonSerialize
public class UserInteraction {

    private String userId;
    private LocalDateTime createdAt;
    private Integer pageId;
    private Action action;

    public UserInteraction() {
    }

    public UserInteraction(String userId, LocalDateTime createdAt, Integer pageId, Action action) {
        this.userId = userId;
        this.createdAt = createdAt;
        this.pageId = pageId;
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        UserInteraction that = (UserInteraction) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(pageId, that.pageId)
                && action == that.action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, createdAt, pageId, action);
    }

    @Override
    public String toString() {
        return "UserInteraction{" +
                "userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                ", pageId=" + pageId +
                ", action=" + action +
                '}';
    }
}

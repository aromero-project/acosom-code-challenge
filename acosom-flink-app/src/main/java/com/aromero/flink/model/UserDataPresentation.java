package com.aromero.flink.model;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class UserDataPresentation {

    @JsonProperty("region")
    private String region;

    @JsonProperty("totalActions")
    private Double totalActions;

    //@JsonProperty("userLevelAverage")
    //private Map<String, Double> userLevelAverage;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    public UserDataPresentation() {

    }

    public UserDataPresentation(String region, Double totalActions, LocalDateTime createdAt) {
        this.region = region;
        this.totalActions = totalActions;
        this.createdAt = createdAt;
    }

    private UserDataPresentation(Builder builder) {
        region = builder.region;
        totalActions = builder.totalActions;
        createdAt = builder.createdAt;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Double getTotalActions() {
        return totalActions;
    }

    public void setTotalActions(Double totalActions) {
        this.totalActions = totalActions;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static final class Builder {
        private String region;
        private Double totalActions;
        private LocalDateTime createdAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder region(String val) {
            region = val;
            return this;
        }

        public Builder totalActions(Double val) {
            totalActions = val;
            return this;
        }

        public Builder createdAt(LocalDateTime val) {
            createdAt = val;
            return this;
        }

        public UserDataPresentation build() {
            return new UserDataPresentation(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDataPresentation that = (UserDataPresentation) o;
        return Objects.equals(region, that.region) && Objects.equals(totalActions, that.totalActions) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, totalActions, createdAt);
    }

    @Override
    public String toString() {
        return "UserDataPresentation{" +
                "region='" + region + '\'' +
                ", totalActions=" + totalActions +
                ", createdAt=" + createdAt +
                '}';
    }
}


package com.aromero.flink.schema;

import com.aromero.flink.model.UserDataPresentation;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.PropertyAccessor;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDataPresentationSerializationSchema implements SerializationSchema<UserDataPresentation> {

    static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    Logger logger = LoggerFactory.getLogger(UserDataPresentationSerializationSchema.class);

    @Override
    public byte[] serialize(UserDataPresentation dataPresentation) {
        if (objectMapper == null) {
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        }

        String json = null;
        try {
            json = objectMapper.writeValueAsString(dataPresentation);
            return json.getBytes();
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse JSON", e);
        }
        return new byte[0];
    }
}

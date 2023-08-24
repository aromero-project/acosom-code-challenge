package com.aromero.flink.schema;

import com.aromero.flink.model.UserInformation;
import com.aromero.flink.model.UserInteraction;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class UserInteractionDeserializationSchema implements DeserializationSchema<UserInteraction> {

    static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public UserInteraction deserialize(byte[] bytes) throws IOException {
        return objectMapper.readValue(bytes, UserInteraction.class);
    }

    @Override
    public boolean isEndOfStream(UserInteraction userInteraction) {
        return false;
    }

    @Override
    public TypeInformation<UserInteraction> getProducedType() {
        return TypeInformation.of(UserInteraction.class);
    }
}

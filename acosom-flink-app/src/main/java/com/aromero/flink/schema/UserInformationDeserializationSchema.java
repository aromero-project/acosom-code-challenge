package com.aromero.flink.schema;

import com.aromero.flink.model.UserInformation;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class UserInformationDeserializationSchema implements DeserializationSchema<UserInformation> {

    static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public UserInformation deserialize(byte[] bytes) throws IOException {
        return objectMapper.readValue(bytes, UserInformation.class);
    }

    @Override
    public boolean isEndOfStream(UserInformation userInformation) {
        return false;
    }

    @Override
    public TypeInformation<UserInformation> getProducedType() {
        return TypeInformation.of(UserInformation.class);
    }
}

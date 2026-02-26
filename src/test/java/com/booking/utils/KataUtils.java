package com.booking.utils;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

public class KataUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Convert any POJO structure to json
     * @param payload to serialize to JSON
     * @return String
     */
    public static String serialize(Object payload) {
        if (payload == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException jsonProcessingException) {
            // TODO implement Logger
            jsonProcessingException.printStackTrace();
        }
        return null;
    }

    /**
     * Convert any json to given POJO structure
     * @param json to deserialize
     * @param t to POJO structure after deserialization
     * @return String
     */
    // Convert any json to given POJO structure
    public static <T> T deserialize(String json, Class<T> t) {
        if (json == null || json.isBlank()) {
            return null;
        }
        try {
            return mapper.readValue(json, t);
        } catch (JsonProcessingException jsonProcessingException) {
            // TODO implement Logger
            jsonProcessingException.printStackTrace();
        }
        return null;
    }
}

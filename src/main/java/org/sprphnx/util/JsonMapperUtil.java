package org.sprphnx.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMapperUtil {
    private static ObjectMapper objectMapper;

    private JsonMapperUtil() {
        // private constructor to prevent instantiation
    }

    public static synchronized ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
        }
        return objectMapper;
    }
}
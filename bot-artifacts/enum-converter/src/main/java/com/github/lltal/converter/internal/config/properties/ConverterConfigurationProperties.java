package com.github.lltal.converter.internal.config.properties;

import java.util.HashMap;
import java.util.Map;

public class ConverterConfigurationProperties {
    private static final Map<String, Object> properties = new HashMap<>();

    public static void putProperty(String key, Object value) {
        properties.put(key, value);
    }

    public static Object getProperty(String key) {
        return properties.get(key);
    }

    public static Map<String, Object> getProperties() {
        return new HashMap<>(properties);
    }
}

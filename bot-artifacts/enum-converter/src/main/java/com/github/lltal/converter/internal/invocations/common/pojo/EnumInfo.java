package com.github.lltal.converter.internal.invocations.common.pojo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class EnumInfo {
    private final Map<String, Object> enumTypesByUserNames = new HashMap<>();
    private final Map<Object, String> userNamesByEnumTypes = new HashMap<>();
}

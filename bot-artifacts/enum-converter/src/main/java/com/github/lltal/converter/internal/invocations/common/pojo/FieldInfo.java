package com.github.lltal.converter.internal.invocations.common.pojo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;

@Data
@RequiredArgsConstructor
public class FieldInfo {
    @NonNull
    private Field field;
    private String userView;
    private Object enumView;
}

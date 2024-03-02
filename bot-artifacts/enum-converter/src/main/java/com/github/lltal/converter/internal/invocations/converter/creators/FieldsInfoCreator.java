package com.github.lltal.converter.internal.invocations.converter.creators;

import com.github.lltal.converter.internal.invocations.common.pojo.FieldInfo;
import com.github.lltal.converter.shared.annotation.ConverteeField;

import java.util.Arrays;
import java.util.List;

public class FieldsInfoCreator {

    public static List<FieldInfo> createFeildsInfo(Class<?> convertee) {

        return Arrays.stream(convertee.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ConverteeField.class))
                .map(FieldInfo::new)
                .toList();
    }
}

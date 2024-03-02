package com.github.lltal.converter.internal.invocations.converter.extractors;

import com.github.lltal.converter.internal.invocations.common.pojo.FieldInfo;
import com.github.lltal.converter.shared.annotation.ConverteeField;

import java.lang.reflect.Field;
import java.util.List;

public class UserFieldNameExctractor {

    public static void extractName(List<FieldInfo> fields) {

        fields.forEach(fieldInfo -> {
            Field field = fieldInfo.getField();
            ConverteeField converteeField = field.getDeclaredAnnotation(ConverteeField.class);
            fieldInfo.setUserView(converteeField.stringView());
        });
    }
}

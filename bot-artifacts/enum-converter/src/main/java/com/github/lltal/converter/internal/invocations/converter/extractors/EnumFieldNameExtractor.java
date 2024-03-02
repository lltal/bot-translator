package com.github.lltal.converter.internal.invocations.converter.extractors;

import com.github.lltal.converter.internal.invocations.common.pojo.FieldInfo;

import java.util.List;

public class EnumFieldNameExtractor {

    public static void extractName(List<FieldInfo> fields, Object[] enumConstants) {

        for (int fieldIndex = 0; fieldIndex < fields.size(); fieldIndex++) {

            fields.get(fieldIndex)
                    .setEnumView(enumConstants[fieldIndex]);
        }
    }
}

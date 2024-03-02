package com.github.lltal.converter.internal.invocations.converter.creators;

import com.github.lltal.converter.internal.invocations.common.pojo.EnumInfo;
import com.github.lltal.converter.internal.invocations.common.pojo.FieldInfo;

import java.util.List;

public class EnumInfoCreator {

    public static EnumInfo createEnumInfo(List<FieldInfo> fields) {

        EnumInfo enumInfo = new EnumInfo();

        fields.forEach(
                field -> {
                    enumInfo.getEnumTypesByUserNames()
                            .put(field.getUserView(), field.getEnumView());
                    enumInfo.getUserNamesByEnumTypes()
                            .put(field.getEnumView(), field.getUserView());
                });

        return enumInfo;
    }
}

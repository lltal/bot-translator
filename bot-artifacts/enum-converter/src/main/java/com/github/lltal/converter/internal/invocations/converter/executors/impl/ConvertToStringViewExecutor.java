package com.github.lltal.converter.internal.invocations.converter.executors.impl;

import com.github.lltal.converter.internal.invocations.common.ifc.ReturnableMethodExecutor;
import com.github.lltal.converter.internal.invocations.common.pojo.EnumInfo;

public class ConvertToStringViewExecutor implements ReturnableMethodExecutor<String> {

    private final EnumInfo enumInfo;

    public ConvertToStringViewExecutor(EnumInfo enumInfo) {
        this.enumInfo = enumInfo;
    }

    @Override
    public String execute(Object[] args) {

        Object enumView = args[0];
        return enumInfo.getUserNamesByEnumTypes().get(enumView);
    }
}

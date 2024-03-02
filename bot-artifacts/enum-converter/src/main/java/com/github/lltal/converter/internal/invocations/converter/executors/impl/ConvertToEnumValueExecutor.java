package com.github.lltal.converter.internal.invocations.converter.executors.impl;

import com.github.lltal.converter.internal.invocations.common.ifc.ReturnableMethodExecutor;
import com.github.lltal.converter.internal.invocations.common.pojo.EnumInfo;

public class ConvertToEnumValueExecutor implements ReturnableMethodExecutor<Object> {

    private final EnumInfo enumInfo;

    public ConvertToEnumValueExecutor(EnumInfo enumInfo) {
        this.enumInfo = enumInfo;
    }

    @Override
    public Object execute(Object[] args) {

        String userView = (String) args[0];
        return enumInfo.getEnumTypesByUserNames().get(userView);
    }
}

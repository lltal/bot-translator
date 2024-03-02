package com.github.lltal.converter.internal.invocations.converter.executors;

import com.github.lltal.converter.internal.invocations.common.ifc.ReturnableMethodExecutor;
import com.github.lltal.converter.internal.invocations.common.pojo.EnumInfo;
import com.github.lltal.converter.internal.invocations.converter.executors.impl.ConvertToEnumValueExecutor;
import com.github.lltal.converter.internal.invocations.converter.executors.impl.ConvertToStringViewExecutor;
import com.github.lltal.converter.shared.ifc.AbstractConverter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConverterMethodExecutorFactory {

    public static Map<Method, ReturnableMethodExecutor> getExecutors(
            EnumInfo enumInfo
    ) {
        Map<Method, ReturnableMethodExecutor> executors = new HashMap<>();

        Arrays.stream(AbstractConverter.class.getDeclaredMethods())
                .forEach(method -> selectExecutor(method, executors, enumInfo));

        return executors;
    }

    private static void selectExecutor(
            Method method,
            Map<Method, ReturnableMethodExecutor> executors,
            EnumInfo enumInfo
    ) {
        switch (method.getName()) {
            case "convertToEnumValue" -> {
                executors.put(method, new ConvertToEnumValueExecutor(enumInfo));
            }
            case "convertToStringView" -> {
                executors.put(method, new ConvertToStringViewExecutor(enumInfo));
            }
        }
    }
}

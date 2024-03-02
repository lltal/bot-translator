package com.github.lltal.converter.internal.invocations.converter;

import com.github.lltal.converter.internal.invocations.common.ifc.ReturnableMethodExecutor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class ConverterInvocationHandler implements InvocationHandler {
    private final Map<Method, ReturnableMethodExecutor> executors;

    public ConverterInvocationHandler(
            Map<Method, ReturnableMethodExecutor> executors
    ) {
        this.executors = executors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.getName().equals("toString")) {
            return "ConverterProxy";
        }
        return executors.get(method).execute(args);
    }
}

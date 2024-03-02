package com.github.lltal.filler.internal.invocations.filler;

import com.github.lltal.filler.internal.invocations.filler.handlers.impl.FilleeFieldHandlerImpl;
import com.github.lltal.filler.internal.invocations.common.ifc.MethodExecutor;
import com.github.lltal.filler.internal.invocations.common.pojo.DtoInfo;
import com.github.lltal.filler.internal.invocations.filler.executors.FillerExecutorMethodFactory;
import com.github.lltal.filler.shared.annotation.Fillee;
import com.github.lltal.filler.shared.ifc.AbstractFiller;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class FillerBeanFactory {

    public static void regFillerBean(
            ConfigurableApplicationContext applicationContext,
            DtoInfo dtoInfo,
            Fillee fillee
    ) {
        if(fillee.fillerBeanName().isEmpty()) {
            return;
        }

        dtoInfo.getFields().values()
                .forEach(fieldInfo ->
                        fieldInfo.setFilleeHandler(
                                new FilleeFieldHandlerImpl(
                                        fieldInfo.getField())));

        Map<Method, MethodExecutor> executors =
                FillerExecutorMethodFactory.getExecutors(dtoInfo.getFields());

        Object fillerBean = Proxy.newProxyInstance(
                dtoInfo.getClass().getClassLoader(),
                new Class[]{AbstractFiller.class},
                new FillerInvocationHandler(executors));

        applicationContext
                .getBeanFactory()
                .registerSingleton(
                        fillee.fillerBeanName(),
                        fillerBean);
    }
}

package com.github.lltal.converter.internal.invocations.converter;

import com.github.lltal.converter.internal.invocations.common.ifc.ReturnableMethodExecutor;
import com.github.lltal.converter.internal.invocations.common.pojo.EnumInfo;
import com.github.lltal.converter.internal.invocations.common.pojo.FieldInfo;
import com.github.lltal.converter.internal.invocations.converter.executors.ConverterMethodExecutorFactory;
import com.github.lltal.converter.shared.annotation.Convertee;
import com.github.lltal.converter.shared.ifc.AbstractConverter;
import com.github.lltal.converter.internal.invocations.converter.creators.FieldsInfoCreator;
import com.github.lltal.converter.internal.invocations.converter.creators.EnumInfoCreator;
import com.github.lltal.converter.internal.invocations.converter.extractors.UserFieldNameExctractor;
import com.github.lltal.converter.internal.invocations.converter.extractors.EnumFieldNameExtractor;
import org.springframework.context.ConfigurableApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

public class ConverterBeanFactory {

    public static void regConverterBean(
            ConfigurableApplicationContext applicationContext,
            Class<?> convertee
    ) {
        List<FieldInfo> fields = FieldsInfoCreator.createFeildsInfo(convertee);

        UserFieldNameExctractor.extractName(fields);
        EnumFieldNameExtractor.extractName(fields, convertee.getEnumConstants());

        EnumInfo enumInfo = EnumInfoCreator.createEnumInfo(fields);

        Map<Method, ReturnableMethodExecutor> executors =
                ConverterMethodExecutorFactory
                        .getExecutors(enumInfo);

        Object resolverBean = Proxy.newProxyInstance(
                convertee.getClassLoader(),
                new Class[]{AbstractConverter.class},
                new ConverterInvocationHandler(executors));

        applicationContext
                .getBeanFactory()
                .registerSingleton(
                        convertee.getAnnotation(Convertee.class).converterBeanName(),
                        resolverBean);
    }
}

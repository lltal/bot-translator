package com.github.lltal.converter.shared.config;

import com.github.lltal.converter.internal.config.properties.ConverterConfigurationProperties;
import com.github.lltal.converter.internal.constants.ConverterConstants;
import com.github.lltal.converter.internal.invocations.converter.ConverterBeanFactory;
import com.github.lltal.converter.shared.annotation.Convertee;
import lombok.NonNull;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;
import java.util.Set;

public class ConverterContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(@NonNull ConfigurableApplicationContext applicationContext) {

        Map<String, Object> properties = ConverterConfigurationProperties.getProperties();
        properties.entrySet()
                .stream()
                .filter(pair -> pair.getKey().contains(ConverterConstants.PACKAGES_TO_SCAN_PROPERTY))
                .forEach(pair -> createBeansForPackage(applicationContext, (String) pair.getValue()));
    }

    private void createBeansForPackage(
            @NonNull ConfigurableApplicationContext applicationContext,
            String packageToScan
    ) {
        Reflections reflections = new Reflections(packageToScan, Scanners.SubTypes);
        Set<Class<? extends Enum>> enums = reflections.getSubTypesOf(Enum.class);

        enums
                .stream()
                .filter(enm -> enm.isAnnotationPresent(Convertee.class))
                .forEach(convertee ->
                        ConverterBeanFactory.regConverterBean(applicationContext, convertee));
    }
}

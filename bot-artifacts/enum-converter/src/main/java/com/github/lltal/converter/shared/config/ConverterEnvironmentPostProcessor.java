package com.github.lltal.converter.shared.config;

import com.github.lltal.converter.internal.config.properties.ConverterConfigurationProperties;
import com.github.lltal.converter.internal.constants.ConverterConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

public class ConverterEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(
            ConfigurableEnvironment environment,
            SpringApplication application
    ) {
        Object packageToScan;
        int index = 0;

        while ((packageToScan = environment.getProperty(createMapKey(index++))) != null) {

            ConverterConfigurationProperties.putProperty(
                    ConverterConstants.PACKAGES_TO_SCAN_PROPERTY, packageToScan);
        }
    }

    private String createMapKey(int index) {
        return String.format(
                "%s[%d]",
                ConverterConstants.PACKAGES_TO_SCAN_PROPERTY,
                index);
    }
}

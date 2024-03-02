package com.github.lltal.botcopier.core.config;

import com.github.lltal.botcopier.shared.config.SharedConfig;
import com.github.lltal.copier.redis.config.RedisConfig;
import com.github.lltal.filler.shared.config.FillerConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
@Import({
        SharedConfig.class,
        RedisConfig.class
})
@EntityScan("com.github.lltal.botcopier.core.model")
@ComponentScan("com.github.lltal.botcopier.core.output.psql.repositories")
public class BotConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "telegram.bot")
    public BotProperties botProperties() {
        return new BotProperties();
    }
}

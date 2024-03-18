package com.github.lltal.botcopier.core.config;

import com.github.lltal.botcopier.shared.config.SharedConfig;
import com.github.lltal.copier.redis.config.RedisConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@Import({
        SharedConfig.class,
        RedisConfig.class
})
@EntityScan("com.github.lltal.botcopier.core.model")
@EnableJpaRepositories("com.github.lltal.botcopier.core.output.psql.repositories")
@ComponentScan("com.github.lltal.botcopier.core")
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

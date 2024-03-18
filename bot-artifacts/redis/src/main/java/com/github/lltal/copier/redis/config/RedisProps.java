package com.github.lltal.copier.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "redis")
@Data
@Component
public class RedisProps {
    private String host;
    private int port;
}

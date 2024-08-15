package com.github.lltal.botcopier.core;

import com.github.lltal.botcopier.core.config.BotConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import({
        BotConfig.class
})
public class BotCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotCoreApplication.class, args);
    }
}

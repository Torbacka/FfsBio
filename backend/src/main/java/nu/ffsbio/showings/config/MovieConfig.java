package nu.ffsbio.showings.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableAutoConfiguration
@ConfigurationProperties(prefix="movie")
public class MovieConfig {
    private String domain;

    public String getDomain() {
        return domain;
    }

}

package nu.ffsbio.showings.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("movie")
public class MovieConfig {

    private String domain;
    private String imagePath;
    private String sfUrl;

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDomain() {
        return domain;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getSfUrl() {
        return sfUrl;
    }

    public void setSfUrl(String sfUrl) {
        this.sfUrl = sfUrl;
    }
}
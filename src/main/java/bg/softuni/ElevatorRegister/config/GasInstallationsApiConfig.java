package bg.softuni.ElevatorRegister.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gas.api")
public class GasInstallationsApiConfig {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public GasInstallationsApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}


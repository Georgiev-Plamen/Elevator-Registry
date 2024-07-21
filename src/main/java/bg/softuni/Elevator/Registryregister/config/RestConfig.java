package bg.softuni.Elevator.Registryregister.config;

import bg.softuni.Elevator.Registryregister.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.client.RestClient;

import java.util.Map;
@Configuration
public class RestConfig {

//    @Bean("genericRestClient")
//    public RestClient genericRestClient() {
//        return RestClient.create();
//    }

    @Bean("gasInstallationRestClient")
    public RestClient gasInstallationRestClient(GasInstallationsApiConfig gasInstallationsApiConfig) {
        return RestClient
                .builder()
                .baseUrl(gasInstallationsApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}

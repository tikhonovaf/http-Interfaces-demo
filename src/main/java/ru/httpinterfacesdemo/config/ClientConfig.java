package ru.httpinterfacesdemo.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.httpinterfacesdemo.client.PetClient;
import ru.httpinterfacesdemo.client.StoreClient;

@Configuration
public class ClientConfig {

    @Value("${services.pet-service.url}") // Убедитесь, что ключи совпадают с yaml
    private String petServiceUrl;

    @Value("${services.store-service.url}")
    private String storeServiceUrl;

    @Bean
    public PetClient petClient(WebClient.Builder builder) {
        return createClient(builder, petServiceUrl, PetClient.class);
    }

    @Bean
    public StoreClient storeClient(WebClient.Builder builder) {
        return createClient(builder, storeServiceUrl, StoreClient.class);
    }
    private <T> T createClient(WebClient.Builder builder, String url, Class<T> clientClass) {
        WebClient webClient = builder.baseUrl(url).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient)).build();
        return factory.createClient(clientClass);
    }
}
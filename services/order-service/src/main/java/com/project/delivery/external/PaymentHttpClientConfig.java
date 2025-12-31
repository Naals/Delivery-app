package com.project.delivery.external;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;



@Configuration
public class PaymentHttpClientConfig {

    @Value("${payment-service.base-url}")
    private String baseUrl;

    @Bean
    RestClient paymentRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    PaymentHttpClient paymentHttpClient(RestClient restClient) {
        return HttpServiceProxyFactory.builder()
                .exchangeAdapter(RestClientAdapter.create(restClient))
                .build()
                .createClient(PaymentHttpClient.class);
    }
}

//package com.web.application.config;
//
//import io.netty.channel.ChannelOption;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.reactive.ReactorClientHttpConnector;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.netty.http.client.HttpClient;
//
//
//@Configuration
//public class WebClientConfig {
//
//    @Value("${erp.api.base-url}")
//    private String BASE_URL;
//
//    @Value("${erp.api.token}")
//    private String TOKEN;
//
//    @Bean
//    public WebClient webClient() {
//
//        HttpClient httpClient = HttpClient.create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
//
//        return WebClient.builder()
//                .baseUrl(BASE_URL)
//                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(1024 * 1024 * 1))
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .defaultHeaders(headers -> {
//                    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//                    headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN);
//                })
//                .build();
//
//    }
//
//}

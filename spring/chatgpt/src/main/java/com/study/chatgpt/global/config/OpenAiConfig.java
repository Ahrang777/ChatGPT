package com.study.chatgpt.global.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Getter
@Configuration
@RequiredArgsConstructor
public class OpenAiConfig {
    private final OpenAiProps openAiProps;

    /**
     * 외부의 OpenAI API 요청을 위한 WebClient
     * @return
     */
    @Bean
    public WebClient openAiWebClient() {
        return WebClient.builder()
                .baseUrl(openAiProps.baseUrl()) // OpenAI API의 기본 URL 설정
                .defaultHeader("Authorization", "Bearer " + openAiProps.secretKey())    // API 요청 시 사용될 기본 인증 헤더 설정
                .build();
    }

}

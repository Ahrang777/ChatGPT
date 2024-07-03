package com.study.chatgpt.global.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OpenAI 사용을 위한 설정값
 * @param secretKey
 * @param baseUrl
 */
@ConfigurationProperties(prefix = "openai")
public record OpenAiProps(String secretKey, String baseUrl, String prompt) {
    // @ConfigurationProperties 에서 snake_case, camelCase 자동 매핑 지원

}

package com.study.chatgpt.domain.gpt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 프롬프트 요청 DTO : gpt-3.5-turbo-instruct, babbage-002, davinci-002
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompletionRequest {
    // 사용할 모델
    private String model;

    // 사용할 프롬프트 명령어
    private String prompt;

    // 프롬프트의 다양성을 조절할 명령어(default : 1)
    private float temperature = 1;

    // 최대 사용할 토큰(default : 16)
    private int maxTokens = 16;
}

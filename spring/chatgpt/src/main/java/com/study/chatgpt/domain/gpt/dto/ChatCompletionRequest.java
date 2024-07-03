package com.study.chatgpt.domain.gpt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 새로운 모델에 대한 요청 객체를 관리합니다. : gpt-4, gpt-4 turbo, gpt-3.5-turbo
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionRequest {
    // 사용할 모델
    private String model;

    private List<ChatCompletionMessage> messages;
}

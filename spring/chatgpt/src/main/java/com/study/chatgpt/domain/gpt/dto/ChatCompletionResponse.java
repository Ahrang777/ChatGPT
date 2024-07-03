package com.study.chatgpt.domain.gpt.dto;

import java.util.List;

public record ChatCompletionResponse(List<Choice> choices) {

    public record Choice(int index, ChatCompletionMessage message) {
    }
}

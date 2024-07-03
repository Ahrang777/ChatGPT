package com.study.chatgpt.domain.gpt.service;

import com.study.chatgpt.domain.gpt.dto.ChatCompletionRequest;
import reactor.core.publisher.Mono;

public interface ChatGptService {
//    List<Map<String, Object>> modelList();
//
//    Map<String, Object> isValidModel(String modelName);
//
//    Map<String, Object> legacyPrompt(CompletionRequest completionRequest);
//
//    Map<String, Object> prompt(ChatCompletionRequest chatCompletionRequest);

    Mono<String> getRecipe(ChatCompletionRequest request);
}

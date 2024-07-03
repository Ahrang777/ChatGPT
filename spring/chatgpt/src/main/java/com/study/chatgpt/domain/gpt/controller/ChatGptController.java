package com.study.chatgpt.domain.gpt.controller;

import com.study.chatgpt.domain.gpt.dto.ChatCompletionRequest;
import com.study.chatgpt.domain.gpt.dto.ChatCompletionsMessageResponse;
import com.study.chatgpt.domain.gpt.service.ChatGptService;
import com.study.chatgpt.global.common.dto.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/openAi")
public class ChatGptController {
    private final ChatGptService chatGptService;

    @GetMapping
    public ResponseEntity<Message<String>> isLive() {
        return ResponseEntity.ok().body(Message.success("서비스 생존"));
    }

    @PostMapping("/recipe")
    public Mono<ResponseEntity<Message<ChatCompletionsMessageResponse>>> getRecipe(@RequestBody ChatCompletionRequest request) {

        return chatGptService.getRecipe(request).map(message -> ResponseEntity.ok().body(Message.success(new ChatCompletionsMessageResponse(message))));
    }

//    /**
//     * [API] ChatGPT 모델 리스트를 조회합니다.
//     */
//    @GetMapping("/modelList")
//    public ResponseEntity<List<Map<String, Object>>> selectModelList() {
//        List<Map<String, Object>> result = chatGptService.modelList();
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    /**
//     * [API] ChatGPT 유효한 모델인지 조회합니다.
//     *
//     * @param modelName
//     * @return
//     */
//    @GetMapping("/model")
//    public ResponseEntity<Map<String, Object>> isValidModel(@RequestParam(name = "modelName") String modelName) {
//        Map<String, Object> result = chatGptService.isValidModel(modelName);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    /**
//     * [API] Legacy ChatGPT 프롬프트 명령을 수행합니다. : gpt-3.5-turbo-instruct, babbage-002, davinci-002
//     *
//     * @param completionRequest {}
//     * @return ResponseEntity<Map < String, Object>>
//     */
//    @PostMapping("/legacyPrompt")
//    public ResponseEntity<Map<String, Object>> selectLegacyPrompt(@RequestBody CompletionRequest completionRequest) {
//        log.debug("param :: " + completionRequest.toString());
//        Map<String, Object> result = chatGptService.legacyPrompt(completionRequest);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
//
//    /**
//     * [API] 최신 ChatGPT 프롬프트 명령어를 수행합니다. : gpt-4, gpt-4 turbo, gpt-3.5-turbo
//     *
//     * @param chatCompletionRequest
//     * @return
//     */
//    @PostMapping("/prompt")
//    public ResponseEntity<Map<String, Object>> selectPrompt(@RequestBody ChatCompletionRequest chatCompletionRequest) {
//        log.debug("param :: " + chatCompletionRequest.toString());
//        Map<String, Object> result = chatGptService.prompt(chatCompletionRequest);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }


}

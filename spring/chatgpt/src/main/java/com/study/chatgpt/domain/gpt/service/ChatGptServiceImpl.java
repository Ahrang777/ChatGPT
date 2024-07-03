package com.study.chatgpt.domain.gpt.service;

import com.study.chatgpt.domain.gpt.dto.ChatCompletionRequest;
import com.study.chatgpt.domain.gpt.dto.ChatCompletionResponse;
import com.study.chatgpt.global.config.OpenAiProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGptServiceImpl implements ChatGptService {
    private final OpenAiProps openAiProps;
    private final WebClient webClient;

    // WebClient를 이용하고 request를 통해 받은 messages의 role, content를 이용하여 ChatGPT에 질문 chat
    // 질문한 결과로 받은 내용을 반환
    @Override
    public Mono<String> getRecipe(ChatCompletionRequest request) {
        return webClient.post()
                .uri(openAiProps.prompt())
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChatCompletionResponse.class)
                .map(response -> response.choices().get(0).message().content());

    }

    //    @Value("${openai.url.model}")
//    private String modelUrl;
//
//    @Value("${openai.url.model-list}")
//    private String modelListUrl;
//
//    @Value("${openai.url.prompt}")
//    private String promptUrl;
//
//    @Value("${openai.url.legacy-prompt}")
//    private String legacyPromptUrl;
//
//    /**
//     * 사용 가능한 모델 리스트를 조회하는 비즈니스 로직
//     *
//     * @return List<Map < String, Object>>
//     */
//    @Override
//    public List<Map<String, Object>> modelList() {
//        log.debug("[+] 모델 리스트를 조회합니다.");
//        List<Map<String, Object>> resultList = null;
//
//        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
//        HttpHeaders headers = openAiConfig.httpHeaders();
//
//        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
//        ResponseEntity<String> response = openAiConfig
//                .restTemplate()
//                .exchange(modelUrl, HttpMethod.GET, new HttpEntity<>(headers), String.class);
//        try {
//            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
//            ObjectMapper om = new ObjectMapper();
//            Map<String, Object> data = om.readValue(response.getBody(), new TypeReference<>() {
//            });
//
//            // [STEP4] 응답 값을 결과값에 넣고 출력을 해봅니다.
//            resultList = (List<Map<String, Object>>) data.get("data");
//            for (Map<String, Object> object : resultList) {
//                log.debug("ID: " + object.get("id"));
//                log.debug("Object: " + object.get("object"));
//                log.debug("Created: " + object.get("created"));
//                log.debug("Owned By: " + object.get("owned_by"));
//            }
//        } catch (JsonMappingException e) {
//            log.debug("JsonMappingException :: " + e.getMessage());
//        } catch (JsonProcessingException e) {
//            log.debug("JsonProcessingException :: " + e.getMessage());
//        } catch (RuntimeException e) {
//            log.debug("RuntimeException :: " + e.getMessage());
//        }
//        return resultList;
//    }
//
//    /**
//     * 모델이 유효한지 확인하는 비즈니스 로직
//     *
//     * @param modelName {}
//     * @return Map<String, Object>
//     */
//    @Override
//    public Map<String, Object> isValidModel(String modelName) {
//        log.debug("[+] 모델이 유효한지 조회합니다. 모델 : " + modelName);
//        Map<String, Object> result = new HashMap<>();
//
//        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
//        HttpHeaders headers = openAiConfig.httpHeaders();
//
//        // [STEP2] 통신을 위한 RestTemplate을 구성합니다.
//        ResponseEntity<String> response = openAiConfig
//                .restTemplate()
//                .exchange(modelListUrl + "/" + modelName, HttpMethod.GET, new HttpEntity<>(headers), String.class);
//        try {
//            // [STEP3] Jackson을 기반으로 응답값을 가져옵니다.
//            ObjectMapper om = new ObjectMapper();
//            result = om.readValue(response.getBody(), new TypeReference<>() {
//            });
//        } catch (JsonProcessingException e) {
//            log.debug("JsonMappingException :: " + e.getMessage());
//        } catch (RuntimeException e) {
//            log.debug("RuntimeException :: " + e.getMessage());
//        }
//        return result;
//    }
//
//    /**
//     * ChatGTP 프롬프트 검색
//     *
//     * @param completionRequest completionDto
//     * @return Map<String, Object>
//     */
//    @Override
//    public Map<String, Object> legacyPrompt(CompletionRequest completionRequest) {
//        log.debug("[+] 레거시 프롬프트를 수행합니다.");
//
//        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
//        HttpHeaders headers = openAiConfig.httpHeaders();
//
//        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
//        HttpEntity<CompletionRequest> requestEntity = new HttpEntity<>(completionRequest, headers);
//        ResponseEntity<String> response = openAiConfig
//                .restTemplate()
//                .exchange(legacyPromptUrl, HttpMethod.POST, requestEntity, String.class);
//
//        Map<String, Object> resultMap = new HashMap<>();
//        try {
//            ObjectMapper om = new ObjectMapper();
//            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
//            resultMap = om.readValue(response.getBody(), new TypeReference<>() {
//            });
//        } catch (JsonProcessingException e) {
//            log.debug("JsonMappingException :: " + e.getMessage());
//        } catch (RuntimeException e) {
//            log.debug("RuntimeException :: " + e.getMessage());
//        }
//        return resultMap;
//    }
//
//    /**
//     * 신규 모델에 대한 프롬프트
//     *
//     * @param chatCompletionRequest {}
//     * @return chatCompletionDto
//     */
//    @Override
//    public Map<String, Object> prompt(ChatCompletionRequest chatCompletionRequest) {
//        log.debug("[+] 신규 프롬프트를 수행합니다.");
//
//        Map<String, Object> resultMap = new HashMap<>();
//
//        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
//        HttpHeaders headers = openAiConfig.httpHeaders();
//
//        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
//        HttpEntity<ChatCompletionRequest> requestEntity = new HttpEntity<>(chatCompletionRequest, headers);
//        ResponseEntity<String> response = openAiConfig
//                .restTemplate()
//                .exchange(promptUrl, HttpMethod.POST, requestEntity, String.class);
//        try {
//            // [STEP6] String -> HashMap 역직렬화를 구성합니다.
//            ObjectMapper om = new ObjectMapper();
//            resultMap = om.readValue(response.getBody(), new TypeReference<>() {
//            });
//        } catch (JsonProcessingException e) {
//            log.debug("JsonMappingException :: " + e.getMessage());
//        } catch (RuntimeException e) {
//            log.debug("RuntimeException :: " + e.getMessage());
//        }
//        return resultMap;
//    }
}

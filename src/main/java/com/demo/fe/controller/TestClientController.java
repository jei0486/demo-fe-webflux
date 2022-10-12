package com.demo.fe.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Tag(name = "Test APIs", description = "Test APIs for demo purpose")
@RequiredArgsConstructor
@RestController
public class TestClientController {

    final WebClient webClient;
    private static final String uri = "http://localhost:8088/slow-service-strings";


    @GetMapping("/test")
    public Mono<String> doTest() {
        return webClient.get()
                .uri("/webclient/test-create")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping(value = "/strings-non-blocking")
    public Flux<String> getTweetsNonBlocking() {

        log.info("Starting NON-BLOCKING Controller!");
        Flux<String> stringFlux = WebClient.create()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(String.class);

        stringFlux.subscribe(s -> log.info(s));
        log.info("Exiting NON-BLOCKING Controller!");
        return stringFlux;
    }


}

package com.demo.fe.service;

import com.demo.fe.model.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {

    final WebClient webClient;

    public Mono<Board> insertBoard(Board board){

        String uri = "http://localhost:8088/board/insert";
        Mono<Board> stringMono = WebClient.create()
                .post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(board)
                //.body(Mono.just(board),Board.class)
                .retrieve()
                .bodyToMono(Board.class);

        return stringMono;
    }



//    public Mono<Board> insertBoard(Board board){
//
//        return webClient.mutate()
//                .baseUrl("http://localhost:8088/board")
//                .build()
//                .post()
//                .uri("http://localhost:8088/board/insert")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .bodyValue(board)
//                .retrieve()
//                .bodyToMono(Board.class);
//    }

}

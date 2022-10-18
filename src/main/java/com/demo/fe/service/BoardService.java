package com.demo.fe.service;

import com.demo.fe.model.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {

    final WebClient webClient;

    // 게시물 작성
    public Mono<Board> insertBoard(Board board){

        String uri = "http://localhost:8088/board/insert";
        Mono<Board> boardMono = WebClient.create()
                .post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(board)
                .retrieve()
                .bodyToMono(Board.class);

        return boardMono;
    }

    // 게시물 리스트
    public Mono<List<Board>> getBoardList(){
        String uri = "http://localhost:8088/board";
        Mono<List<Board>> result = WebClient.create()
                .get()
                .uri(uri)
                .exchange()
                .flatMapMany(res -> res.bodyToFlux(Board.class))
                .collectList();
        return result;
    }

    // 게시물 상세보기
    public Mono<Board> getDetailBoard(Long boardId){

        // http://localhost:8088/board/{boardId}
       Mono<Board> result = WebClient.create()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("localhost")
                        .port(8088)
                        .path("/board/{id}")
                        .build(boardId))
                .retrieve()
                .bodyToMono(Board.class);

        return result;
    }
    
    // 게시물 수정
    public Mono<Board> updateBoard(Long boardId, Board board){
        Mono<Board> result = WebClient.create()
                .put()
                // .uri("/board/{boardId}", boardId)
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("localhost")
                        .port(8088)
                        .path("/board/{id}")
                        .build(boardId))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(board)
                .retrieve()
                .bodyToMono(Board.class);
        return result;
    }

   // 게시물 삭제
    public Mono<Void> deleteBoard(Long boardId){
        return WebClient.create()
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .scheme("http")
                        .host("localhost")
                        .port(8088)
                        .path("/board/{id}")
                        .build(boardId))
                .retrieve()
                // delete() 함수 의 특성상 response는 Void.class 로 처리
                .bodyToMono(Void.class);

    }



}

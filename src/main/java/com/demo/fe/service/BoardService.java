package com.demo.fe.service;

import com.demo.fe.model.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class BoardService {

    @Value("${custom.api.url.demo-api}")
    private String demoApiUrl;

    // 게시물 작성
    public Mono<Board> insertBoard(Board board){

        Mono<Board> boardMono = WebClient.create()
                .post()
                .uri(demoApiUrl + "/board")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(board)
                .retrieve()
                .bodyToMono(Board.class);

        return boardMono;
    }

    // 게시물 리스트
    public Mono<List<Board>> getBoardList(){

        /*
        exchange 메서드는 retrieve 보다 세밀한 컨트롤이 가능한 대신 memory leak 을 주의 해야 한다.
        https://github.com/reactor/reactor-netty/issues/1401
         */
        Mono<List<Board>> result = WebClient.create()
                .get()
                .uri(demoApiUrl + "/board")
                .exchange()
                .flatMapMany(res -> res.bodyToFlux(Board.class))


                .collectList();
        return result;
    }

    // 게시물 상세보기
    public Mono<Board> getDetailBoard(Long boardId){

        URI juri;
        try {
            juri = new URI(demoApiUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        Mono<Board> result = WebClient.create()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme(juri.getScheme())
                        .host(juri.getHost())
                        .port(juri.getPort())
                        .path("/board/{id}")
                        .build(boardId))
                .retrieve()
                .bodyToMono(Board.class);

        return result;
    }
    
    // 게시물 수정
    public Mono<Board> updateBoard(Long boardId, Board board){
        URI juri;
        try {
            juri = new URI(demoApiUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Mono<Board> result = WebClient.create()
                .put()
                // .uri("/board/{boardId}", boardId)
                .uri(uriBuilder -> uriBuilder
                        .scheme(juri.getScheme())
                        .host(juri.getHost())
                        .port(juri.getPort())
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
        URI juri;
        try {
            juri = new URI(demoApiUrl);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return WebClient.create()
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .scheme(juri.getScheme())
                        .host(juri.getHost())
                        .port(juri.getPort())
                        .path("/board/{id}")
                        .build(boardId))
                .retrieve()
                // delete() 함수 의 특성상 response는 Void.class 로 처리
                .bodyToMono(Void.class);

    }



}

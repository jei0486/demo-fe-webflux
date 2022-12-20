package com.demo.fe.service;

import com.demo.fe.config.WebClientConfig;
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

    private final WebClientConfig webclient;

    // 게시물 작성
    public Mono<Board> insertBoard(Board board){
        WebClient client = this.webclient.getWebClient();
        return client
                .post()
                .uri("/boards")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(board)
                .retrieve()
                .bodyToMono(Board.class);

    }

    // 게시물 리스트
    public Mono<List<Board>> getBoardList(){

        /*
        exchange 메서드는 retrieve 보다 세밀한 컨트롤이 가능한 대신 memory leak 을 주의 해야 한다.
        https://github.com/reactor/reactor-netty/issues/1401
         */
        WebClient client = this.webclient.getWebClient();
        return client
                .get()
                .uri("/boards")
                .exchange()
                .flatMapMany(res -> res.bodyToFlux(Board.class))
                .collectList();
    }

    // 게시물 상세보기
    public Mono<Board> getDetailBoard(Long boardId){

        WebClient client = this.webclient.getWebClient();
        return client
                .get()
                .uri("/boards/" + boardId)
                .retrieve()
                .bodyToMono(Board.class);

    }
    
    // 게시물 수정
    public Mono<Board> updateBoard(Long boardId, Board board){
        WebClient client = this.webclient.getWebClient();
        return client
                .put()
                .uri("/boards/" + boardId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(board)
                .retrieve()
                .bodyToMono(Board.class);
    }

   // 게시물 삭제
    public Mono<Void> deleteBoard(Long boardId){
        WebClient client = this.webclient.getWebClient();
        return client
                .delete()
                .uri("/boards/" + boardId)
                .retrieve()
                // delete() 함수 의 특성상 response는 Void.class 로 처리
                .bodyToMono(Void.class);
    }



}

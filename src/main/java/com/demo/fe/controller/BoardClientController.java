package com.demo.fe.controller;

import com.demo.fe.model.Board;
import com.demo.fe.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Tag(name = "Board APIs", description = "Board APIs for demo purpose")
@RequiredArgsConstructor
@RestController
public class BoardClientController {

    final WebClient webClient;

    final BoardService boardService;

    @Operation(summary = "게시물 작성", description = "1) 게시물 작성")
    @PostMapping("/board/insert")
    public Mono<Board> insertBoard(Board board){
        return boardService.insertBoard(board);
    }


    @Operation(summary = "게시물 리스트", description = "2) 게시물 리스트")
    @GetMapping("/board/list")
    public void getBoardList(){

    }

    @Operation(summary = "게시물 상세보기", description = "3) 게시물 상세보기")
    @GetMapping("/board/{board-id}")
    public void getDetailBoard(Long boardId){

    }

    @Operation(summary = "게시물 수정", description = "4) 게시물 수정")
    @PutMapping("/board/{board-id}")
    public void updateBoard(Board board){

    }

    @Operation(summary = "게시물 삭제", description = "5) 게시물 삭제")
    @DeleteMapping("/board/{board-id}")
    public void deleteBoard(Long boardId){

    }

    
}

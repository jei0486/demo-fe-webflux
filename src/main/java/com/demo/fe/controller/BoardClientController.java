package com.demo.fe.controller;

import com.demo.fe.model.Board;
import com.demo.fe.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Tag(name = "Board APIs", description = "Board APIs for demo purpose")
@RequiredArgsConstructor
@RestController
public class BoardClientController {

    final WebClient webClient;

    final BoardService boardService;

    @Operation(summary = "게시물 작성", description = "1) 게시물 작성")
    @PostMapping("/board/insert")
    private Mono<Board> insertBoard(Board board){
        return boardService.insertBoard(board);
    }


    @Operation(summary = "게시물 리스트", description = "2) 게시물 리스트")
    @GetMapping("/board/list")
    private Mono<List<Board>> getBoardList(){
        return  boardService.getBoardList();
    }

    @Operation(summary = "게시물 상세보기", description = "3) 게시물 상세보기")
    @GetMapping("/board/{board-id}")
    private  Mono<Board> getDetailBoard(@PathVariable ("board-id") Long boardId){
        return boardService.getDetailBoard(boardId);
    }

    @Operation(summary = "게시물 수정", description = "4) 게시물 수정")
    @PutMapping("/board/{board-id}")
    private Mono<Board> updateBoard(@PathVariable ("board-id") Long boardId,Board board){
        return boardService.updateBoard(boardId,board);
    }

    @Operation(summary = "게시물 삭제", description = "5) 게시물 삭제")
    @DeleteMapping("/board/{board-id}")
    private Mono<Void> deleteBoard(@PathVariable ("board-id") Long boardId){
        return boardService.deleteBoard(boardId);
    }

}

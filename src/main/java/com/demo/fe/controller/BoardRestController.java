package com.demo.fe.controller;

import com.demo.fe.model.Board;
import com.demo.fe.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Board APIs", description = "Board APIs for demo purpose")
@RequestMapping("/boards")
@RequiredArgsConstructor
@RestController
public class BoardRestController {

    private final BoardService boardService;

    @Operation(summary = "게시물 작성", description = "1) 게시물 작성")
    @PostMapping("")
    private Mono<Board> insertBoard(@Valid Board board){
        return boardService.insertBoard(board);
    }


    @Operation(summary = "게시물 리스트", description = "2) 게시물 리스트")
    @GetMapping("")
    private Mono<List<Board>> getBoardList(){
        return  boardService.getBoardList();
    }

    @Operation(summary = "게시물 상세보기", description = "3) 게시물+ 상세보기")
    @GetMapping("/{boardId}")
    private  Mono<Board> getDetailBoard(@PathVariable Long boardId){
        return boardService.getDetailBoard(boardId);
    }

    @Operation(summary = "게시물 수정", description = "4) 게시물 수정")
    @PutMapping("/{boardId}")
    private Mono<Board> updateBoard(@PathVariable Long boardId,@Valid Board board){
        return boardService.updateBoard(boardId,board);
    }

    @Operation(summary = "게시물 삭제", description = "5) 게시물 삭제")
    @DeleteMapping("/{boardId}")
    private Mono<Void> deleteBoard(@PathVariable Long boardId){
        return boardService.deleteBoard(boardId);
    }

}

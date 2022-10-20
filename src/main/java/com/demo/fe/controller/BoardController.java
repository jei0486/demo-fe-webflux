package com.demo.fe.controller;

import com.demo.fe.model.Board;
import com.demo.fe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/*
    View Controller
 */
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // 게시물 상세보기
    @GetMapping("/detail/{boardId}")
    private Mono<String> boardDetail(Model model,@PathVariable Long boardId){
        Mono<Board> detailBoard = boardService.getDetailBoard(boardId);
        model.addAttribute("board",detailBoard);
        return Mono.just("board-detail");
    }

    // 게시물 리스트
    @GetMapping("/boardList")
    private Mono<String> boardList(Model model){
        Mono<List<Board>> boardList = boardService.getBoardList();
        model.addAttribute("list",boardList);
        return Mono.just("board-list");
    }

    // 게시물 작성페이지로 이동
    @GetMapping("/page/insert")
    private Mono<String> goInsertPage(Model model){

        return Mono.just("board-insert");
    }

    /*
        게시물 insert 후 상세보기 페이지로 이동
     */
    @PostMapping("/insertBoard")
    private Mono<String> insertBoardAndDetail(Model model,@Valid Board board) {
        Mono<Board> detailBoard = boardService.insertBoard(board);
        model.addAttribute("board",detailBoard);
        // 상세보기로 이동
       return Mono.just("board-detail");
    }

    // 수정 페이지로 이동
    @GetMapping("/page/update/{boardId}")
    private Mono<String> goUpdatePage (Model model,@PathVariable Long boardId){
        model.addAttribute("board",boardService.getDetailBoard(boardId));
        return Mono.just("board-update");
    }

}

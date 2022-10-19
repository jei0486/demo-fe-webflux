package com.demo.fe.controller;

import com.demo.fe.model.Board;
import com.demo.fe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/*
    View Controller
 */
@RequiredArgsConstructor
@Controller
public class BoardController {

    final BoardService boardService;

    // 게시물 리스트 테스트
    @GetMapping("/list")
    private Mono<String> getBoardList(Model model){
        Mono<List<Board>> boardList = boardService.getBoardList();
        model.addAttribute("board",boardList);
        return Mono.just("test");
    }

    // 게시물 상세보기
    @GetMapping("/detail/{boardId}")
    private Mono<String> getBoardList(Model model,@PathVariable Long boardId){
        Mono<Board> detailBoard = boardService.getDetailBoard(boardId);
        model.addAttribute("board",detailBoard);
        return Mono.just("detail");
    }

    // 게시물 리스트
    @GetMapping("/board_list")
    private Mono<String> getBoard(Model model){
        Mono<List<Board>> boardList = boardService.getBoardList();
        model.addAttribute("list",boardList);
        return Mono.just("board_list");
    }

    // 게시물 작성페이지로 이동
    @GetMapping("/page/insert")
    private Mono<String> add(Model model){
        return Mono.just("board_insert");
    }

    /*
        게시물 insert 후 상세보기 페이지로 이동
     */
    @PostMapping("/board_insert")
    private Mono<String> addComplete(Model model,Board board) {
        Mono<Board> detailBoard = boardService.insertBoard(board);
        model.addAttribute("board",detailBoard);
        // 상세보기로 이동
       return Mono.just("detail");
    }

    // 수정 페이지로 이동
    @GetMapping("/page/update/{boardId}")
    private Mono<String> update (Model model,@PathVariable Long boardId){
        model.addAttribute("board",boardService.getDetailBoard(boardId));
        return Mono.just("board_update");
    }

}

package com.demo.fe.controller;

import com.demo.fe.model.Board;
import com.demo.fe.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.util.List;

/*
    View Controller
 */
@RequiredArgsConstructor
@Controller
public class BoardController {

    final BoardService boardService;

    // 게시물 리스트
    @RequestMapping("/list")
    private Mono<String> getBoardList(Model model){
        Mono<List<Board>> boardList = boardService.getBoardList();
        model.addAttribute("board",boardList);
        return Mono.just("test");
    }

    // 게시물 상세보기
    @RequestMapping("/detail/{boardId}")
    private Mono<String> getBoardList(Model model,@PathVariable Long boardId){
        Mono<Board> detailBoard = boardService.getDetailBoard(boardId);
        model.addAttribute("board",detailBoard);
        return Mono.just("detail");
    }

    // 게시물 리스트
    @RequestMapping("/board_list")
    private Mono<String> getBoard(Model model){
        Mono<List<Board>> boardList = boardService.getBoardList();
        model.addAttribute("list",boardList);
        return Mono.just("board_list");
    }

    // 게시물 작성페이지로 이동
    @RequestMapping("/board_add")
    private Mono<String> add(Model model){
        return Mono.just("board_add");
    }

    @PostMapping("/board_insert")
    private Mono<String> addComplete(Model model,Board board) {
        Mono<Board> detailBoard = boardService.insertBoard(board);
        model.addAttribute("board",detailBoard);
        // 상세보기로 이동
       return Mono.just("detail");
    }


}
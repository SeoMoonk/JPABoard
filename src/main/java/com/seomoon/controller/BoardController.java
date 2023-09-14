package com.seomoon.controller;

import com.seomoon.Entity.Board;
import com.seomoon.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    @ResponseBody
    public List<Board> getList() {

        List<Board> list = boardService.getList();

        for(Board article:list) {
            System.out.println(article.getArticleNo());
            System.out.println(article.getTitle());
            System.out.println(article.getContent());
            System.out.println(article.getWrite_id());
        }

        return list;
    }


}

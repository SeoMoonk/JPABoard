package com.seomoon.controller;

import com.seomoon.model.Entity.Bulletin;
import com.seomoon.service.BulletinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BulletinController {

    private final BulletinService bulletinService;

    @GetMapping("/list")
    @ResponseBody
    public List<Bulletin> getList() {

        List<Bulletin> list = bulletinService.getList();

//        for(Bulletin article:list) {
//            System.out.println(article.getArticleNo());
//            System.out.println(article.getTitle());
//            System.out.println(article.getContent());
//            System.out.println(article.getWrite_id());
//        }

        return list;
    }


}

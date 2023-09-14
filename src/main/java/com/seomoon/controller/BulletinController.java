package com.seomoon.controller;

import com.seomoon.model.Entity.Bulletin;
import com.seomoon.service.BulletinService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BulletinController {

    private final BulletinService bulletinService;

    Logger logger = LoggerFactory.getLogger("com.seomoon.controller.BoardController");

    @RequestMapping({"/list", "/"})
    public String getArticleList(Model model) {

        System.out.println("List Mapping");

        List<Bulletin> articleList = bulletinService.getBulletinList();
        model.addAttribute("dataList", articleList);

        return "list";
    }

    @GetMapping({"/add"})
    public String writeArticle() {

        System.out.println("Write Mapping");

        return "write";
    }

    @PostMapping("/addarticle")
    public String addArticle(@RequestParam(value = "i_title") String title,
                             @RequestParam(value = "i_content") String content) {


         bulletinService.generateBulletin(title, content, "ADMIN");

        return "redirect:list";
    }

    @GetMapping("/view")
    public String viewArticle(@RequestParam(value = "no") String articleNo, Model model) {

        logger.debug("조회한 게시글 ==========> " + articleNo);

//        파라미터는 무조건 String으로 넘어오기 때문에, String -> Integer 변환을 하여 사용해야 한다?

        Bulletin bulletinByNo = bulletinService.getBulletinByNo(Long.parseLong(articleNo));

        model.addAttribute("article", bulletinByNo);

        return "view";
    }

    @PostMapping("/edit")
    public String editArticle(@RequestParam(value="articleNo") String articleNo,
                              @RequestParam(value="title") String title,
                              @RequestParam(value="content") String content,
                              RedirectAttributes attr) {

        System.out.println(articleNo);
        System.out.println(title);
        System.out.println(content);

        bulletinService.modifyBulletin(articleNo, title, content);

        attr.addAttribute("no", articleNo);

        return "redirect:view";
    }

    @PostMapping("/delete")
    public String deleteArticle (@RequestParam(value="articleNo") String articleNo) {

        bulletinService.removeBulletin(Long.parseLong(articleNo));

        return "redirect:list";
    }

    @PostMapping("/edit/cancel")
    public String editCancel(@RequestParam(value="articleNo") String articleNo,
                             RedirectAttributes attr) {

        attr.addAttribute("no", articleNo);

        return "redirect:/board/view";
    }

    @GetMapping(value = "/info")
    @ResponseBody
    public Map<String, String> getInfo() {

        Map<String, String> map = new HashMap<>();
        map.put("name", "sku project");
        map.put("version", "1.0");
        map.put("author", "Tom");

        return map;
    }


}

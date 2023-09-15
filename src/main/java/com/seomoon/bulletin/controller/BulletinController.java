package com.seomoon.bulletin.controller;

import com.seomoon.bulletin.entity.Bulletin;
import com.seomoon.bulletin.service.BulletinService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BulletinController {

    private final BulletinService bulletinService;

    Logger logger = LoggerFactory.getLogger("com.seomoon.controller.BoardController");

    @RequestMapping("/list")
    public String getArticleList(Model model, @RequestParam(value="page", defaultValue="1") int page) {

        Page<Bulletin> pagingDataList = bulletinService.getPageList(page-1);
        model.addAttribute("pagingDataList", pagingDataList);

        return "list";
    }

    @GetMapping({"/add"})
    @PreAuthorize("isAuthenticated()")
    public String writeArticle() {

        System.out.println("Write Mapping");

        return "write";
    }

    @PostMapping("/addarticle")
    @PreAuthorize("isAuthenticated()")
    public String addArticle(@RequestParam(value = "i_title") String title,
                             @RequestParam(value = "i_content") String content,
                             Principal principal) {

        String username = principal.getName();

        bulletinService.generateBulletin(title, content, username);

        return "redirect:list";
    }

    @GetMapping("/view")
    public String viewArticle(@RequestParam(value = "no") String articleNo, Model model) {

        logger.debug("조회한 게시글 ==========> " + articleNo);

        //TODO 파라미터는 무조건 String으로 넘어오기 때문에, String -> Integer 변환을 하여 사용해야 한다? => 이제 얘기가 다름.

        Bulletin bulletinByNo = bulletinService.getBulletinByNo(Long.parseLong(articleNo));

        model.addAttribute("article", bulletinByNo);

        return "view";
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated()")
    public String editArticle(@RequestParam(value="articleNo") String articleNo,
                              @RequestParam(value="title") String title,
                              @RequestParam(value="content") String content,
                              RedirectAttributes attr) {

        bulletinService.modifyBulletin(articleNo, title, content);

        attr.addAttribute("no", articleNo);

        return "redirect:view";
    }

    @PostMapping("/delete")
    @PreAuthorize("isAuthenticated()")
    public String deleteArticle (@RequestParam(value="articleNo") String articleNo) {

        bulletinService.removeBulletin(Long.parseLong(articleNo));

        return "redirect:list";
    }

    @PostMapping("/edit/cancel")
    @PreAuthorize("isAuthenticated()")
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

    @PostMapping("/search")
    public String getSearchList(@RequestParam(value="keyword") String keyword,
                                Model model) {

        System.out.println("search mapping");

        Page<Bulletin> findList = bulletinService.getFindList(keyword);

        model.addAttribute("pagingDataList", findList);

        //fixme
        return "redirect:/board/list";
    }

}

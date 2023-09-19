package com.seomoon.catdog.controller;

import com.seomoon.base.service.FilterTransfer;
import com.seomoon.base.service.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class CatDogController {

    private final FilterTransfer filterTransfer;
    private final RequestUtil requestUtil;

    Map<String, String> map = new HashMap<>();

    @PostMapping("/uploadfile")
    public Map<String, String> uploadImage(@RequestPart(value = "uploadfile") MultipartFile uploadfile) throws IllegalStateException, IOException, IOException {
        // TODO Auto-generated method stub

        String catordog = filterTransfer.webToLocal(uploadfile);
        map.put("catordog", catordog);
        System.out.println(map);

        return map;
    }

    @GetMapping("/dogandcat")
    public String getClassifier() throws Exception {

        return "classifier";
    }

}

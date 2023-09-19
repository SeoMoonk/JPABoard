package com.seomoon.catdog.controller;

import com.seomoon.base.service.FilterTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class FileController {

    @Autowired
    private FilterTransferService filterTransferService;

    Map<String, String> map = new HashMap<>();

    @PostMapping("/uploadfile")
    public Map<String, String> uploadImage(@RequestPart(value = "uploadfile") MultipartFile uploadfile) throws IllegalStateException, IOException {
        // TODO Auto-generated method stub

        String catordog = filterTransferService.webToLocal(uploadfile);
        map.put("catordog", catordog);
        System.out.println(map);

        return map;
    }

}

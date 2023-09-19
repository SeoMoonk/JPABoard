package com.seomoon.catdog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class CatDogController {

    @GetMapping("/dogandcat")
    public String getClassifier() throws Exception {

        return "classifier";
    }

}

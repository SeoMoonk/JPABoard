package com.seomoon.boardUser.controller;

import com.seomoon.boardUser.model.BoardUserCreateForm;
import com.seomoon.boardUser.service.BoardUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class BoardUserController {

    private final BoardUserService boardUserService;

    @GetMapping("/signup")
    public String signUp(BoardUserCreateForm boardUserCreateForm) {


        return "signup_form";
    }

    @PostMapping("/signup")
    public String singUp(@Valid BoardUserCreateForm joinFormData,
                         BindingResult bindingResult) {

        if(bindingResult.hasErrors()){

            return "signup_form";
        }

        if(!boardUserService.passwordCheck(joinFormData)){
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");

            return "signup_form";
        }

        try {
            boardUserService.create(joinFormData);
        } catch(DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자 입니다.");
            return "signup_form";
        } catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/board/list";
    }


}

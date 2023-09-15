package com.seomoon.boardUser.service;

import com.seomoon.boardUser.model.BoardUserCreateForm;
import com.seomoon.boardUser.model.entity.BoardUser;
import com.seomoon.boardUser.repository.BoardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardUserService {

    private final BoardUserRepository boardUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BoardUser create(BoardUserCreateForm joinDataForm) {

        String username = joinDataForm.getUsername();
        String password = joinDataForm.getPassword1();
        String email = joinDataForm.getEmail();

        BoardUser newBoardUser = BoardUser.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        boardUserRepository.save(newBoardUser);

        return newBoardUser;
    }

    public boolean passwordCheck(BoardUserCreateForm joinFormData){

        String password1 = joinFormData.getPassword1();
        String password2 = joinFormData.getPassword2();

        return password1.equals(password2);
    }

}

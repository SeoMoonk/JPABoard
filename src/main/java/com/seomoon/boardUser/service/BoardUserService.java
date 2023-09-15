package com.seomoon.boardUser.service;

import com.seomoon.boardUser.entity.BoardUser;
import com.seomoon.boardUser.repository.BoardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardUserService {

    private final BoardUserRepository boardUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BoardUser create(String username, String email, String password) {

        BoardUser newBoardUser = BoardUser.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        boardUserRepository.save(newBoardUser);

        return newBoardUser;
    }

}

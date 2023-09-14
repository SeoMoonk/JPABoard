package com.seomoon.service;

import com.seomoon.Entity.Board;
import com.seomoon.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getList() {

        List<Board> allList = boardRepository.findAll();

        return allList;
    }

}

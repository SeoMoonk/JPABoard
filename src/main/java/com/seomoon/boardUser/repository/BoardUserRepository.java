package com.seomoon.boardUser.repository;

import com.seomoon.boardUser.model.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardUserRepository extends JpaRepository <BoardUser, Long> {

    Optional<BoardUser> findByUsername(String username);

}

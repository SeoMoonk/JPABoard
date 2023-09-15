package com.seomoon.boardUser.repository;

import com.seomoon.boardUser.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardUserRepository extends JpaRepository <BoardUser, Long> {


}

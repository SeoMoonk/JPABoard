package com.seomoon.bulletin.repository;

import com.seomoon.bulletin.entity.Bulletin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BulletinRepository extends JpaRepository<Bulletin, Long> {

    Page<Bulletin> findAll(Pageable pageable);
    Page<Bulletin> findByTitleLike(String title, Pageable pageable);


}

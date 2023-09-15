package com.seomoon.bulletin.repository;

import com.seomoon.bulletin.entity.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulletinRepository extends JpaRepository<Bulletin, Long> {


}

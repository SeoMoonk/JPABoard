package com.seomoon.service;

import com.seomoon.model.Entity.Bulletin;
import com.seomoon.repository.BulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BulletinService {

    private final BulletinRepository bulletinRepository;

    public List<Bulletin> getList() {

        List<Bulletin> allList = bulletinRepository.findAll();

        return allList;
    }

}

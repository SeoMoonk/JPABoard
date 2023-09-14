package com.seomoon.service;

import com.seomoon.model.Entity.Bulletin;
import com.seomoon.repository.BulletinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BulletinService {

    private final BulletinRepository bulletinRepository;

    public List<Bulletin> getBulletinList() {

        List<Bulletin> bulletinList = bulletinRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return bulletinList;
    }

    public void createBulletin(Bulletin newBulletin) throws DataAccessException {

        bulletinRepository.save(newBulletin);
    }

    public Bulletin generateBulletin(String title, String content, String writer) throws DataAccessException {

        Bulletin newBulletin = Bulletin.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();

        bulletinRepository.save(newBulletin);

        return newBulletin;
    }

    public Bulletin getBulletinByNo(Long bulletinNo) throws DataAccessException {

        Optional<Bulletin> OBulletin = bulletinRepository.findById(bulletinNo);

        if(OBulletin.isPresent()){
            return OBulletin.get();
        }else{
            return null;
        }
    }

    public void modifyBulletin(String articleNo, String title, String content) throws DataAccessException {


        Bulletin bulletinByNo = getBulletinByNo(Long.parseLong(articleNo));

        bulletinByNo.setTitle(title);
        bulletinByNo.setContent(content);

        bulletinRepository.save(bulletinByNo);
    }

    public void removeBulletin(Long bulletinNo) throws DataAccessException {

        bulletinRepository.deleteById(bulletinNo);
    }

}

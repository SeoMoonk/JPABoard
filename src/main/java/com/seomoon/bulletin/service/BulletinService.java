package com.seomoon.bulletin.service;

import com.seomoon.boardUser.model.entity.BoardUser;
import com.seomoon.boardUser.service.BoardUserService;
import com.seomoon.bulletin.repository.BulletinRepository;
import com.seomoon.bulletin.entity.Bulletin;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BulletinService {

    private final BulletinRepository bulletinRepository;
    private final BoardUserService boardUserService;

    public List<Bulletin> getBulletinList() {

        List<Bulletin> bulletinList = bulletinRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        return bulletinList;
    }

    public void createBulletin(Bulletin newBulletin) throws DataAccessException {

        bulletinRepository.save(newBulletin);
    }

    public Bulletin generateBulletin(String title, String content, String writer) throws DataAccessException {

        BoardUser byUsername = boardUserService.getByUsername(writer);

        Bulletin newBulletin = Bulletin.builder()
                .title(title)
                .content(content)
                .writer(byUsername)
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

    public Page<Bulletin> getPageList(int page) throws DataAccessException {

        Pageable pageable = PageRequest.of(page, 5);
        Page<Bulletin> pageData = bulletinRepository.findAll(pageable);

        return pageData;
    }

    public Page<Bulletin> getFindList(String keyword) throws  DataAccessException {

        Pageable pageable = PageRequest.of(1, 5);
        Page<Bulletin> pageData = bulletinRepository.findByTitleLike("%"+keyword+"%", pageable);

        return pageData;
    }

}

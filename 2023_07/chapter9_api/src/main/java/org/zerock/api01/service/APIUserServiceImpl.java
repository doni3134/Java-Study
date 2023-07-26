package org.zerock.api01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.api01.domain.APIUser;
import org.zerock.api01.repository.APIUserRepository;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class APIUserServiceImpl implements APIUserService {


    private final APIUserRepository apiUserRepository;


    @Override
    public boolean checkUser(String mid, String mpw) {

        log.info("checkUser:" + mid+":" + mpw);

        Optional<APIUser> result = apiUserRepository.findAPIUserByMidAndMpw(mid,mpw);

        //결과가 있는지만 반환
        return result.isPresent();
    }
}












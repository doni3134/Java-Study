package org.zerock.api01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.api01.dto.APITokenDTO;
import org.zerock.api01.util.JWTUtil;

import java.util.Map;

@SpringBootTest
@Log4j2
public class APITokenServiceTests {

    @Autowired
    private APITokenService tokenService;

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testRefresh() {

//        //100분의 유효시간
//        String refreshToken = jwtUtil.generateToken(Map.of("mid","apiuser1"),1);
//
//        log.info("-----------------------------");
//        log.info(refreshToken);
//
//        APITokenDTO apiTokenDTO = tokenService.refreshTokens(refreshToken);
//
//        //만료일이 1시간이 남지 않으면
//        log.info(apiTokenDTO);
//        //RefreshToken은 갱신되지 않음
//        log.info(apiTokenDTO.getRefresh().equals(refreshToken));
    }

}

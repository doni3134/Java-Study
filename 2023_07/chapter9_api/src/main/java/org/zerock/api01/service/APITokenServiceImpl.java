package org.zerock.api01.service;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.api01.dto.APITokenDTO;
import org.zerock.api01.util.JWTUtil;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class APITokenServiceImpl implements  APITokenService{

    private final JWTUtil jwtUtil;

    @Override
    public APITokenDTO makeTokens(String mid) {

        Map<String, Object> claim = Map.of("mid", mid);
        //Access Token 유효기간 1일
        String accessToken = jwtUtil.generateToken(claim, 1);
        //Refresh Token 유효기간 30일
        String refreshToken = jwtUtil.generateToken(claim, 30);

        APITokenDTO apiToken = APITokenDTO.builder().owner(mid)
                .access(accessToken)
                .refresh(refreshToken)
                .build();

        return apiToken;
    }

    @Override
    public APITokenDTO refreshTokens(String refreshToken) throws JwtException {

        //RefreshToken 자체가 문제가 있거나 만료되면 다시 모든걸 시작해야 함
        //Refresh Token 자체의 만료 시간이 넘는다면 JwtException 발생
        Map<String, Object> parseResult = jwtUtil.validateToken(refreshToken);

        log.info(parseResult);

        //Refresh Token의 유효시간이 얼마 남지 않은 경우
        Integer exp = (Integer)parseResult.get("exp");

        Date expTime = new Date(Instant.ofEpochMilli(exp).toEpochMilli() * 1000);

        Date current = new Date(System.currentTimeMillis());

        //만료 시간과 현재 시간의 간격 계산
        //만일 1일 미만인 경우에는 Refresh Token도 다시 생성
        long gapTime = (expTime.getTime() - current.getTime());

        log.info("-----------------------------------------");
        log.info("current: " + current);
        log.info("expTime: " + expTime);
        log.info("gap: " + gapTime );

        String mid = (String)parseResult.get("mid");
        //Access Token 생성
        String accessTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 1);

        //Refresh Token의 유효시간이 충분하다면 그대로
        String refreshTokenValue = refreshToken;

        //유효시간이 부족하다면
        //1000* 60 * 60 * 24  1day
        if(gapTime < (1000 * 60 * 60  ) ){
            log.info("new Refresh Token required...  ");
            refreshTokenValue = jwtUtil.generateToken(Map.of("mid", mid), 30);
        }

        return APITokenDTO.builder()
                .owner(mid)
                .refresh(refreshTokenValue)
                .access(accessTokenValue)
                .build();
    }


}
























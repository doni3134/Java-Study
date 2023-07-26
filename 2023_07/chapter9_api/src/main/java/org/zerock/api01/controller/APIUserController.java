package org.zerock.api01.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.api01.dto.APITokenDTO;
import org.zerock.api01.dto.RequestTokenDTO;
import org.zerock.api01.service.APITokenService;
import org.zerock.api01.service.APIUserService;

@RestController
@Log4j2
@RequiredArgsConstructor
public class APIUserController {

    private final APIUserService apiUserService;

    private final APITokenService tokenService;

    public static class APIUserNotFoundException extends RuntimeException {

    }

    public static class BadRefreshRequestException extends RuntimeException {

    }

    @ApiOperation("Generate Tokens with POST ")
    @PostMapping("/generateToken")
    public APITokenDTO generateToken(@RequestBody RequestTokenDTO requestTokenDTO){

        boolean checkResult = apiUserService.checkUser(requestTokenDTO.getMid(),
                requestTokenDTO.getMpw());

        if(!checkResult){
            throw new APIUserNotFoundException();
        }

        //임시로 아무 값이나 생성
        //return Map.of("ACCESS","11111", "REFRESH","222222");

        return tokenService.makeTokens(requestTokenDTO.getMid());
    }

    @ApiOperation("Refresh Tokens with POST ")
    @PostMapping(value = "/refreshAccessToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public APITokenDTO refreshAccessToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("refresh_token") String refreshToken ){

        log.info("grantType: " + grantType);
        log.info("refreshToken: " + refreshToken);

        if(grantType == null || grantType.equals("refresh_token") == false){
            throw new BadRefreshRequestException();
        }

        return tokenService.refreshTokens(refreshToken);

    }

}

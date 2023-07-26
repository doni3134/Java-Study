package org.zerock.api01.service;

import io.jsonwebtoken.JwtException;
import org.zerock.api01.dto.APITokenDTO;

public interface APITokenService {

    APITokenDTO makeTokens(String mid);

    APITokenDTO refreshTokens(String refreshToken) throws JwtException;

}

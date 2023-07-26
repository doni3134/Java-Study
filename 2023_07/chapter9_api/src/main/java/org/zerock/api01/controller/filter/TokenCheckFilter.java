package org.zerock.api01.controller.filter;

import com.google.gson.Gson;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.api01.controller.filter.exception.AccessTokenException;
import org.zerock.api01.util.JWTUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("-----------------------------------------------");
        log.info("JWTUTIL: " + jwtUtil);

        String path = request.getRequestURI();

        //다른 경로인 경우에는 패스
        if (!path.startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Token check.................");

        try {
            //토큰 검증등이 필요한 부분
            validateAccessToken(request);
            filterChain.doFilter(request, response);
        } catch (AccessTokenException e) {
            makeErrorMessage(e, response);
        } catch (ExpiredJwtException expiredJwtException){
            makeRequestRefreshMessge(expiredJwtException, response);
        }
    }

    private void makeRequestRefreshMessge(ExpiredJwtException expiredJwtException, HttpServletResponse response) {

        Gson gson = new Gson();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String,Object> map = new HashMap<>();
        map.put("ERROR", "Your Access Token has expired");
        map.put("Refresh URL", "/refreshAccessToken");

        String jsonMsg = gson.toJson(map);

        log.info(jsonMsg);

        try(PrintWriter writer = response.getWriter()) {
            writer.write(jsonMsg);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private void makeErrorMessage(AccessTokenException e, HttpServletResponse response ){

        Gson gson = new Gson();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String,Object> map = new HashMap<>();
        map.put("ERROR", e.getMsg());
        map.put("TIME", System.currentTimeMillis());

        String jsonMsg = gson.toJson(map);

        log.info(jsonMsg);

        try(PrintWriter writer = response.getWriter()) {
            writer.write(jsonMsg);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException, ExpiredJwtException {

        String headerStr = request.getHeader("Authorization");

        log.info("Authorization: " + headerStr);

        if(headerStr == null || headerStr.length() < 8){
            throw new AccessTokenException("Access Token Required");
        }

        //Bearer 생략
        String tokenType = headerStr.substring(0,6);
        String tokenStr =  headerStr.substring(7);

        if(tokenType.equalsIgnoreCase("Bearer") == false){
            throw new AccessTokenException("Bad Type Token");
        }

        try{
            Map<String, Object> values = jwtUtil.validateToken(tokenStr);

            return values;
        }catch(MalformedJwtException malformedJwtException){
            log.error("MalformedJwtException----------------------");
            throw new AccessTokenException("Malformed");
        }catch(SignatureException signatureException){
            log.error("SignatureException----------------------");
            throw new AccessTokenException("Signature");
        }
    }
}

package com.ssafy.tofy.util;

import com.ssafy.tofy.exception.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Log4j2
@Service
public class JwtServiceImpl implements JwtService {

    //	SALT는 토큰 유효성 확인 시 사용하기 때문에 외부에 노출되지 않게 주의해야 한다.
    private static final String SALT = "tofySecret";

    private static final int ACCESS_TOKEN_EXPIRE_MINUTES = 1; // 분단위
    private static final int REFRESH_TOKEN_EXPIRE_MINUTES = 2; // 주단위

    @Override
    public <T> String createAccessToken(String key, T data) {
        return create(key, data, "access-token", 1000 * 600 * ACCESS_TOKEN_EXPIRE_MINUTES);
//		return create(key, data, "access-token", 1000 * 10 * ACCESS_TOKEN_EXPIRE_MINUTES);
    }

    @Override
    public <T> String createRefreshToken(String key, T data) {
        return create(key, data, "refresh-token", 1000 * 60 * 60 * 24 * 7 * REFRESH_TOKEN_EXPIRE_MINUTES);
//		return create(key, data, "refresh-token", 1000 * 30 * ACCESS_TOKEN_EXPIRE_MINUTES);
    }

    // Signature 설정에 들어갈 key 생성.
    private byte[] generateKey() {
        byte[] key = null;
        try {
            // charset 설정 안하면 사용자 플랫폼의 기본 인코딩 설정으로 인코딩 됨.
            key = SALT.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            if (log.isInfoEnabled()) {
                e.printStackTrace();
            } else {
                log.error("Making JWT Key Error ::: {}", e.getMessage());
            }
        }

        return key;
    }

    @Override
    public <T> String create(String key, T data, String subject, long expire) {
        // Payload 설정 : 생성일 (IssuedAt), 유효기간 (Expiration),
        // 토큰 제목 (Subject), 데이터 (Claim) 등 정보 세팅.
        Claims claims = Jwts.claims()
                // 토큰 제목 설정 ex) access-token, refresh-token
                .setSubject(subject)
                // 생성일 설정
                .setIssuedAt(new Date())
                // 만료일 설정 (유효기간)
                .setExpiration(new Date(System.currentTimeMillis() + expire));

        // 저장할 data의 key, value
        claims.put(key, data);

        String jwt = Jwts.builder()
                // Header 설정 : 토큰의 타입, 해쉬 알고리즘 정보 세팅.
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                // Signature 설정 : secret key를 활용한 암호화.
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact(); // 직렬화 처리.

        return jwt;
    }

    @Override
    public Map<String, Object> get(String key) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        String jwt = request.getHeader("access-token");
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
        } catch (Exception e) {
//			if (logger.isInfoEnabled()) {
//				e.printStackTrace();
//			} else {
            log.error(e.getMessage());
//			}
            throw new UnAuthorizedException();
//			개발환경
//			Map<String,Object> testMap = new HashMap<>();
//			testMap.put("userid", userid);
//			return testMap;
        }
        Map<String, Object> value = claims.getBody();
        log.info("value : {}", value);
        return value;
    }

    @Override
    public String getUserId() {
        return (String) this.get("user").get("userid");
    }

    //	전달 받은 토큰이 제대로 생성된것인지 확인 하고 문제가 있다면 UnauthorizedException을 발생.
    @Override
    public boolean checkToken(String jwt) {
        try {
//			Json Web Signature? 서버에서 인증을 근거로 인증정보를 서버의 private key로 서명 한것을 토큰화 한것
//			setSigningKey : JWS 서명 검증을 위한  secret key 세팅
//			parseClaimsJws : 파싱하여 원본 jws 만들기
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
//			Claims 는 Map의 구현체 형태
            log.debug("claims: {}", claims);
            return true;
        } catch (Exception e) {
//			if (logger.isInfoEnabled()) {
//				e.printStackTrace();
//			} else {
            log.error(e.getMessage());
//			}
//			throw new UnauthorizedException();
//			개발환경
            return false;
        }
    }
}

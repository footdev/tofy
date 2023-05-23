package com.ssafy.tofy.user.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tofy.user.dto.SelectTag;
import com.ssafy.tofy.user.dto.User;
import com.ssafy.tofy.user.service.UserService;
import com.ssafy.tofy.util.JwtService;
import com.ssafy.tofy.util.Response;
import com.ssafy.tofy.util.Status;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;

@Log4j2
@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtService  jwtService;

    private static final String ACCESS_TOKEN = "access-token";
    private static final String REFRESH_TOKEN = "refresh-token";
    private static final String USER_ID = "userid";

    //회원가입
    @PostMapping(value = "/user/join")
    public ResponseEntity<Object> join(@RequestBody User user) {

        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("user join success")
                .data(null)
                .build();

        try {
            userService.join(user);
            log.info("회원가입 완료 !!!");

        } catch (Exception e) {
            log.error("회원 가입 에러 {}", e.getMessage());

            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("user join error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //로그인
    @PostMapping("/user")
    public ResponseEntity<Object> login(@RequestBody User user) {

        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("login success")
                .data(new HashMap<>())
                .build();

        log.info("{} 로그인 요청", user.getUserId());

        try {
            User retUser = userService.login(user);

            if (retUser != null) {
                String accessToken = jwtService.createAccessToken(USER_ID, retUser.getUserId());
                String refreshToken = jwtService.createRefreshToken(USER_ID, retUser.getUserId());

                userService.saveRefreshToken(retUser.getUserId(), refreshToken);

                log.info("로그인 성공");
                log.info("로그인 accessToken 정보 {} ", accessToken);
                log.info("로그인 refreshToken 정보 {} ", refreshToken);

                res.getData().put(ACCESS_TOKEN, accessToken);
                res.getData().put(REFRESH_TOKEN, refreshToken);

            } else {
                log.error("회원 정보 틀림");
                res.setStatus(Status.FAIL.getStatus());
                res.setMessage("login fail");
            }

        } catch (Exception e) {
            log.error("로그인 실패 {}", e.getMessage());

            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("login error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //로그아웃
    @DeleteMapping("/user/logout/{userId}")
    public ResponseEntity<Object> logout(@PathVariable String userId) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("user logout success")
                .data(null)
                .build();

        try {
            userService.deleteRefreshToken(userId);
            log.info("{} 로그아웃 실행", userId);
        } catch (Exception e) {
            log.error("로그아웃 실패 {}", e.getMessage());

            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("user logout error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //id 중복체크
    @GetMapping("/user/idcheck/{userId}")
    public ResponseEntity<Object> idCheck(@PathVariable String userId) {

        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("user id is unique")
                .data(new HashMap<>())
                .build();

        try {
            User retUser = userService.idCheck(userId);

            if (retUser != null) {
                res.setStatus(Status.FAIL.getStatus());
                res.setMessage("user id is not unique");
                res.getData().put("user", retUser);
            }

        }catch (Exception e) {
            log.error("아이디 중복체크 에러 {}", e.getMessage());

            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("user id check error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //회원정보 수정
    @PutMapping("/user")
    public ResponseEntity<Object> update(@RequestBody User user) {
        Response res = Response.builder()
                .data(new HashMap<>())
                .build();

        try {
            userService.update(user);

            res.setStatus(Status.SUCCESS.getStatus());
            res.setMessage("user info update success");

        } catch (Exception e) {
            log.error("user info update error {}", e.getMessage());

            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("user info update error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //회원 탈퇴
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userId) {
        Response res = Response.builder()
                .data(new HashMap<>())
                .build();

        try {
            userService.delete(userId);

            log.info("{} 회원 탈퇴 요청", userId);

            res.setStatus(Status.SUCCESS.getStatus());
            res.setMessage("user info delete success");

        } catch (Exception e) {
            log.error("{} 회원 탈퇴 에러", e.getMessage());

            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("user info delete error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //access 토큰 재발급
    @PostMapping("/user/refresh")
    public ResponseEntity<Object> refreshToken(@RequestBody User user, HttpServletRequest request) throws SQLException {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("user refresh token issue")
                .data(new HashMap<>())
                .build();
        String token = request.getHeader(REFRESH_TOKEN);

        log.info("user : {} refresh-token : {}", user.getUserId(), token);

       if (jwtService.checkToken(token)) {
           //클라이언트로 부터 온 refresh token과 서버에 저장되어 있는 refresh token이 같은지 검사
           if (token.equals(userService.getRefreshToken(user.getUserId()))) {
                String newAccessToken = jwtService.createAccessToken(USER_ID, user.getUserId());

                log.info("new access token {}", newAccessToken);
                log.info("새로운 접근 토큰 생성 완료");

                res.getData().put(ACCESS_TOKEN, newAccessToken);
           }
       } else {
           log.warn("refresh 토큰도 사용 불가");
           res.setStatus(Status.FAIL.getStatus());
           res.setMessage("refresh token unauthorized");
       }

       return ResponseEntity.ok()
               .body(res);
    }

    //회원 정보 유효성 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> userInfo(@PathVariable String userId, HttpServletRequest request) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("valid token")
                .data(new HashMap<>())
                .build();

        if (jwtService.checkToken(request.getHeader(ACCESS_TOKEN))) {
            try {
                log.info("사용 가능한 토큰");

               User user = userService.userInfo(userId);
               res.getData().put("user", user);

            } catch (Exception e) {
                log.error("정보 조회 실패");
                res.setStatus(Status.ERROR.getStatus());
                res.setMessage("userInfo error");
            }
        } else {
            log.error("사용 불가능 토큰");
            log.error("넘어온 토큰 {}", request.getHeader(ACCESS_TOKEN));
            res.setStatus(Status.FAIL.getStatus());
            res.setMessage("unvalid access token");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    // 회원 가입 시 태그 선택
    // 태그가 있을 때, 태그 no을 value값을 받아서 선택한 value값을 int 배열로 받아와서 방식으로 처리함
    @PostMapping("/user/{userId}/tag")
    public ResponseEntity<Object> selectTag(@PathVariable String userId, @RequestBody int[] tags) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("user select tag success")
                .data(new HashMap<>())
                .build();

        Map<String, Object> param = new HashMap();
        param.put("userId", userId);
        param.put("tagArr", tags);

        try {
        	userService.selectTag(param);
        } catch (Exception e) {
            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("user select tag error");
			log.error("태그 선택 오류 {}", e.getMessage());
		}
		return ResponseEntity.ok().body(res);
    }

    // 회원 자신이 선택한 태그 보이기
    @GetMapping("/user/{userId}/tag")
    public ResponseEntity<Object> selectTag(@PathVariable String userId) {
    	Response res = Response.builder()
    			.status(Status.SUCCESS.getStatus())
    			.message("show user's tag success")
    			.data(new HashMap<>())
    			.build();

    	try {
    		SelectTag[] tagList = userService.getTag(userId);

    		res.getData().put(userId + "'s selectTag", tagList);

    		} catch (Exception e) {
                res.setStatus(Status.ERROR.getStatus());
                res.setMessage("show user's tag error");
    			log.error("태그 선택 오류");
			}
			return ResponseEntity.ok().body(res);
	  }
}

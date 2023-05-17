package com.ssafy.tofy.user.controller;

import com.ssafy.tofy.user.dto.User;
import com.ssafy.tofy.user.service.UserService;
import com.ssafy.tofy.util.JwtService;
import com.ssafy.tofy.util.Response;
import com.ssafy.tofy.util.Status;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Log4j2
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtService  jwtService;

    //회원가입
    @PostMapping(value = "/user/join")
    public ResponseEntity<Object> join(@RequestBody User user) {

        Response res = Response.builder()
                .data(null)
                .build();

        try {
            userService.join(user);

            log.info("회원가입 완료 !!!");

            res.setStatus(Status.SUCCESS.getStatus());
            res.setMessage("user join success");


        } catch (Exception e) {
            log.error("user join error {]", e);

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
                .data(new HashMap<>())
                .build();

        try {
            User retUser = userService.login(user);

            if (retUser != null) {
                String accessToken = jwtService.createAccessToken("userid", retUser.getUserId());
                String refreshToken = jwtService.createRefreshToken("userid", retUser.getUserId());

                userService.saveRefreshToken(retUser.getUserId(), refreshToken);

                log.info("로그인 accessToken 정보 {} ", accessToken);
                log.info("로그인 refreshToken 정보 {} ", refreshToken);

                res.setStatus(Status.SUCCESS.getStatus());
                res.setMessage("login success");

                res.getData().put("access-token", accessToken);
                res.getData().put("refresh-token", refreshToken);

            } else {
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
        } catch (Exception e) {
            log.error("로그아웃 실패 {}", e.getMessage());

            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("user logout error");
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

    //회원정보 조회
    @GetMapping("/user/{userNo}")
    public ResponseEntity<Object> getUser(@PathVariable String userNo) {
        return null;
    }

    //회원 탈퇴
    @DeleteMapping("/user/{userNo}")
    public ResponseEntity<Object> deleteUser(@PathVariable String userNo) {
        Response res = Response.builder()
                .data(new HashMap<>())
                .build();

        try {
            userService.delete(userNo);

            res.setStatus(Status.SUCCESS.getStatus());
            res.setMessage("user info delete success");

        } catch (Exception e) {
            log.error("user info delete error {}", e.getMessage());

            res.setStatus(Status.ERROR.getStatus());
            res.setMessage("user info delete error");
        }

        return ResponseEntity.ok()
                .body(res);
    }

    //유저 아이디 중복 체크
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> idCheck(@PathVariable String userId) {
        return null;
    }

    //여행지 월드컵 결과 list 조회
    @GetMapping("/user/{userNo}/worldcup")
    public ResponseEntity<Object> getWinnerAttractions(@PathVariable String userNo) {
        return null;
    }
}

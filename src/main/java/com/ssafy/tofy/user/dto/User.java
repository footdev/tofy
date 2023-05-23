package com.ssafy.tofy.user.dto;

import lombok.*;

@Getter
@Setter
public class User {

    String userId;
    String userNm;
    String userPw;
    String emailId;
    String emailDomain;
    String adminFl;
    String token;
    String regTime;
}

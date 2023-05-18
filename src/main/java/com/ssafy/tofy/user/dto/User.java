package com.ssafy.tofy.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
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

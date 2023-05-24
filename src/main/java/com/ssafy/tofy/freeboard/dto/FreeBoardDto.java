package com.ssafy.tofy.freeboard.dto;

import com.ssafy.tofy.user.dto.User;

import lombok.Data;

@Data
public class FreeBoardDto {
	private String freeBoardNo;
	private String userNo;
	private String freeBoardCreate;
	private String freeBoardTitle;
	private String freeBoardContent;
	private String freeBoardHit;
	private User user;
}

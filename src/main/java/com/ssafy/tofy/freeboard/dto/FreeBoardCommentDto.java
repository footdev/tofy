package com.ssafy.tofy.freeboard.dto;

import com.ssafy.tofy.user.dto.User;

import lombok.Data;

@Data
public class FreeBoardCommentDto {
	private String commentNo;
	private String userNo;
	private String boardNo;
	private String commentCreate;
	private String commentContent;
	private User user;
}

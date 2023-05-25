package com.ssafy.tofy.notice.dto;

import com.ssafy.tofy.user.dto.User;

import lombok.Data;

@Data
public class NoticeDto {
	
	private String noticeNo;
	private String userNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeCreate;
	private String noticeHit;
	private User user;
	
}

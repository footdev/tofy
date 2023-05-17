package com.ssafy.tofy.notice.dto;

import lombok.Data;

@Data
public class NoticeDto {
	private int noticeNo;
	private int userNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeCreate;
	private int noticeHit;
}

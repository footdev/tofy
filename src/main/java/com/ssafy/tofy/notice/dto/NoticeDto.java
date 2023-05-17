package com.ssafy.tofy.notice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticeDto {
	private String noticeNo;
	private String userNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeCreate;
	private String noticeHit;
}

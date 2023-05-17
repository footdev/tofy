package com.ssafy.tofy.freeboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeBoardDto {
	private String freeBoardNo;
	private String userNo;
	private String freeBoardCreate;
	private String freeBoardTitle;
	private String freeBoardContent;
	private String freeBoardHit;
}

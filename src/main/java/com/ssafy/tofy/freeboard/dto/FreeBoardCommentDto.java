package com.ssafy.tofy.freeboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeBoardCommentDto {
	private String commentNo;
	private String userNo;
	private String boardNo;
	private String commentCreate;
	private String commentContent;
}

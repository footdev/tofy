package com.ssafy.tofy.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SelectTag {
	
	private String userNo;
	private String tagNo;

}

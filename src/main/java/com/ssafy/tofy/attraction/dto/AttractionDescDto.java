package com.ssafy.tofy.attraction.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttractionDescDto {
	private String contentId;  //여행지 고유 번호
	private String desc;	   //여행지 상세 설명
}

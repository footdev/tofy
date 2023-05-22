package com.ssafy.tofy.attraction.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttractionDescJoinDto {
	private String title;           //여행지 이름
	private String addr;            //여행지 주소 (1차)
	private String addr2;           //여행지 주소 (세부)
	private String firstImage;      //대표 이미지
	private String secondImage;     //대체 이미지
	private String desc;	   //여행지 상세 설명
}

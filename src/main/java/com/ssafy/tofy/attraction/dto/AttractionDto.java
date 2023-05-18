package com.ssafy.tofy.attraction.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttractionDto {
	private String contentId;       //여행지 고유 번호
	private String contentTypeId;   //여행지 Type
	private String title;           //여행지 이름
	private String addr;            //여행지 주소 (1차)
	private String addr2;           //여행지 주소 (세부)
	private String zipCode;
	private String tel;
	private String firstImage;      //대표 이미지
	private String secondImage;     //대체 이미지
	private String readCnt;
	private String sidoCode;        //시도 코드(검색)
	private String gugunCode;       //구군 코드(검색)
	private String latitude;        //위도
	private String longitude;       //경도
	private String mLevel;
}

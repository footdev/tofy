package com.ssafy.tofy.attraction.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Gugun {
	private String gugunCode;  //구군 코드
	private String sidoCode;   //시도 코드
	private String gugunName;	   //구군 이름
}

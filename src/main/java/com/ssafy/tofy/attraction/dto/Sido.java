package com.ssafy.tofy.attraction.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sido {
	private String sidoCode;  //시도 코드
	private String sidoName;      //시도 이름
}


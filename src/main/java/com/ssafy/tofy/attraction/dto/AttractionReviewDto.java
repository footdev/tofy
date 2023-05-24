package com.ssafy.tofy.attraction.dto;

import lombok.Data;

@Data
public class AttractionReviewDto {
	private String attractionReviewNo;
	private String contentId;
	private String userNo;
	private String attractionReviewContent;
	private String attractionReviewScore;
	private String attractionReviewImg;
	private String attractionReviewCreate;
}

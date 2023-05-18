package com.ssafy.tofy.attraction.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttractionReviewDto {
	private String attractionReviewNo;
	private String contentId;
	private String userNo;
	private String attractionReviewContent;
	private String attractionReviewScore;
	private String attractionReviewImg;
	private String attractionReviewCreate;
}

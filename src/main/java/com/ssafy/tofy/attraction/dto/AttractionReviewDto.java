package com.ssafy.tofy.attraction.dto;

import com.ssafy.tofy.user.dto.User;

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
	private User user;
}

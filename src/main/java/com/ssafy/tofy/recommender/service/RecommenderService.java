package com.ssafy.tofy.recommender.service;

import java.util.List;

import com.ssafy.tofy.attraction.dto.AttractionDto;

public interface RecommenderService {
	
	List<AttractionDto> recommendAttraction(String contentId);
	List<AttractionDto> recommendAttractionByUser(String userNo);

}

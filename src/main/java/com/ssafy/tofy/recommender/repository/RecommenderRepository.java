package com.ssafy.tofy.recommender.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tofy.attraction.dto.AttractionDto;

@Mapper
public interface RecommenderRepository {
	
	List<AttractionDto> recommendAttraction(String contentId);
	List<AttractionDto> recommendAttractionByUser(String userNo);

}

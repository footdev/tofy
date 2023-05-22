package com.ssafy.tofy.attraction.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tofy.attraction.dto.AttractionDescDto;
import com.ssafy.tofy.attraction.dto.AttractionDto;
import com.ssafy.tofy.attraction.dto.AttractionReviewDto;
import com.ssafy.tofy.attraction.dto.Gugun;
import com.ssafy.tofy.attraction.dto.Sido;

@Mapper
public interface AttractionRepository {
	public List<Sido> selectSidoList() throws Exception; // 시/도 정보 조회
	public List<Gugun> selectGugunListBySidoCode(String sidoCode);// 구/군 정보 조회
	public AttractionDescDto selectDescription(String contentId) throws Exception; //Modal 여행지 상세 정보 추출
	public List<AttractionDto> selectTripList(Map<String, String> param);  // 여행지 id 값을 바탕으로 정보 가져오기
	
	public List<AttractionReviewDto> listReview(String contentId);
	public void writeReview(AttractionReviewDto review);
	public void modifyReview(AttractionReviewDto review);
	public void deleteReview(String reviewNo);
	public AttractionDto selectDetail(String contentId);
}
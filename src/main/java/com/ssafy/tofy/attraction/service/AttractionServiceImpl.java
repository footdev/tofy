package com.ssafy.tofy.attraction.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.tofy.attraction.dto.AttractionDescDto;
import com.ssafy.tofy.attraction.dto.AttractionDto;
import com.ssafy.tofy.attraction.dto.AttractionReviewDto;
import com.ssafy.tofy.attraction.dto.Gugun;
import com.ssafy.tofy.attraction.dto.Sido;
import com.ssafy.tofy.attraction.repository.AttractionRepository;

@Service("AttractionServiceImpl")
public class AttractionServiceImpl implements AttractionService{
	
	private AttractionRepository attractionRepo;

	public AttractionServiceImpl(AttractionRepository attractionRepo) {
		super();
		this.attractionRepo = attractionRepo;
	}

	@Override
	public List<Sido> selectSidoList() throws Exception {
		return attractionRepo.selectSidoList();
	}

	@Override
	public List<Gugun> selectGugunListBySidoCode(String sidoCode) {
		return attractionRepo.selectGugunListBySidoCode(sidoCode);
	}

	@Override
	public List<AttractionDto> attractionInfo(Map<String, String> hMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AttractionDescDto selectDescription(String contentId) throws Exception {
		return attractionRepo.selectDescription(contentId);
	}

	@Override
	public List<AttractionDto> selectTrips(List<String> tList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttractionReviewDto> listReview(String contentId) {
		return attractionRepo.listReview(contentId);
	}

	@Override
	public void writeReview(AttractionReviewDto review) {
		attractionRepo.writeReview(review);
	}

	@Override
	public void modifyReview(AttractionReviewDto review) {
		attractionRepo.modifyReview(review);
	}

	@Override
	public void deleteReview(String reviewNo) {
		attractionRepo.deleteReview(reviewNo);
	}
}

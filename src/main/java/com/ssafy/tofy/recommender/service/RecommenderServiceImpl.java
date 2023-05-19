package com.ssafy.tofy.recommender.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.tofy.attraction.dto.AttractionDto;
import com.ssafy.tofy.recommender.repository.RecommenderRepository;

@Service("RecommenderServiceImpl")
public class RecommenderServiceImpl implements RecommenderService {

	private RecommenderRepository recommenderRepo;

	public RecommenderServiceImpl(RecommenderRepository recommenderRepo) {
		super();
		this.recommenderRepo = recommenderRepo;
	}

	@Override
	public List<AttractionDto> recommendAttraction(String contentId) {
		return recommenderRepo.recommendAttraction(contentId);
	}
}

package com.ssafy.tofy.recommender.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.tofy.attraction.dto.AttractionDto;
import com.ssafy.tofy.recommender.repository.RecommenderRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RecommenderServiceImpl implements RecommenderService {

	private RecommenderRepository recommenderRepo;

	public RecommenderServiceImpl(RecommenderRepository recommenderRepo) {
		super();
		this.recommenderRepo = recommenderRepo;
	}

	@Override
	public List<AttractionDto> recommendAttraction(String contentId) {
		log.info(contentId);
		return recommenderRepo.recommendAttraction(contentId);
	}
}

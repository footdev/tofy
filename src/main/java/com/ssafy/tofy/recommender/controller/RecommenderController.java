package com.ssafy.tofy.recommender.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tofy.attraction.dto.AttractionDto;
import com.ssafy.tofy.recommender.service.RecommenderService;
import com.ssafy.tofy.util.Response;
import com.ssafy.tofy.util.Status;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = {"*"})
public class RecommenderController {

	@Autowired
	private RecommenderService recommendService;
	
	public RecommenderController(RecommenderService recommendService) {
		super();
		this.recommendService = recommendService;
	}
	
	// 여행지에 대한 연관 여행지 추천
	@GetMapping("/recommend/{conentNo}")
	public ResponseEntity<Object> recommendAttraction(@PathVariable String conentNo) {
        Response res = Response.builder()
                .status(Status.SUCCESS.getStatus())
                .message("attraction recommend success")
                .data(new HashMap<>())
                .build();
        
        try {
        	List<AttractionDto> recommendList = recommendService.recommendAttraction(conentNo);
        	res.getData().put("recommend attractions", recommendList);
        	log.info("여행지에 대한 연관 여행지 추천 리스트 불러오기 성공");
        	
        } catch (Exception e) {
			// TODO: handle exception
        	log.error("여행지에 대한 연관 여행지 추천 불러오기 실패" + e.getMessage());
        	res.setStatus(Status.ERROR.getStatus());
            res.setMessage("error get recoomend Attractions");
		}
        
        return ResponseEntity.ok()
                .body(res);
	}
}

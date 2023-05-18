package com.ssafy.tofy.attraction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tofy.attraction.dto.AttractionDescDto;
import com.ssafy.tofy.attraction.dto.AttractionReviewDto;
import com.ssafy.tofy.attraction.dto.Gugun;
import com.ssafy.tofy.attraction.dto.Sido;
import com.ssafy.tofy.attraction.service.AttractionService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/attraction")
@CrossOrigin(origins = {"*"})
public class AttractionController {
	
	private AttractionService attractionService;
	
	public AttractionController(@Qualifier("AttractionServiceImpl") AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}

	@GetMapping("/sido")
	public ResponseEntity<Object> getFreeBoards() {
		try {
			log.info("시/도 정보 조회");
			List<Sido> list = attractionService.selectSidoList();
			
			if (list == null || list.size() == 0) log.warn("시/도 정보 불러오지 못함");
			
			for (Sido sido : list) {
				log.info(sido.toString());
			}

			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			log.info("시/도 정보 조회 실패");
			return ResponseEntity.badRequest().build();
		}
	}
	

	@GetMapping("/gugun/{sidoCode}")
	public ResponseEntity<Object> getGugunsBySidoCode(@PathVariable String sidoCode) {

		log.info("구군 정보를 {}번 코드로 조회", sidoCode);

		List<Gugun> list = attractionService.selectGugunListBySidoCode(sidoCode);
		
		if (list == null || list.size() == 0) {
			log.error("구군 정보를 불러오지 못했습니다...");
			return ResponseEntity.badRequest()
					.build();
		}
		return ResponseEntity.ok()
				.body(list);
	}

	@GetMapping("/{contentId}")
//	public ResponseEntity<Object> getBoard(@PathVariable String contentId, @RequestBody Map<String, String> map) {
	public ResponseEntity<Object> getBoard(@PathVariable String contentId, @RequestBody Map<String, String> map) {
		try {
			AttractionDescDto attractionDesc = attractionService.selectDescription(contentId);

			log.info(contentId + "번 여행지 정보 조회");
			return ResponseEntity.ok().body(attractionDesc);
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/{contentId}/review")
	public ResponseEntity<Object> getReview(@PathVariable String contentId) {
		try {
			log.info("여행지에 대한 리뷰 리스트 출력 호출");
			List<AttractionReviewDto> list = attractionService.listReview(contentId);
			
			for (AttractionReviewDto attractionReview : list) {
				log.info(attractionReview.toString());
			}

			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/{contentId}/review")
	public ResponseEntity<Object> writeReview(@PathVariable String contentId, @RequestBody AttractionReviewDto review) {
		try {
			review.setContentId(contentId);
			attractionService.writeReview(review);
			
			log.info("{}번 여행지 리뷰 생성", contentId);
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/{contentId}/review")
	public ResponseEntity<Object> modifyReview(@RequestBody AttractionReviewDto review) {
		try {
			attractionService.modifyReview(review);
			
			log.info("{}번 여행지 리뷰 수정", review);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{contentId}/review/{reviewNo}")
	public ResponseEntity<Object> deleteBoardComment(@PathVariable String contentId, @PathVariable String reviewNo) {
		try {
			log.info("{}번 리뷰 삭제", reviewNo);
			
			attractionService.deleteReview(reviewNo);
			
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}

package com.ssafy.tofy.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tofy.freeboard.dto.FreeBoardDto;
import com.ssafy.tofy.plan.dto.Plan;
import com.ssafy.tofy.plan.dto.PlanDetail;
import com.ssafy.tofy.plan.service.PlanService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class PlanController {
	
	PlanService planService;

	public PlanController(PlanService planService) {
		super();
		this.planService = planService;
	}
	
	@GetMapping("/plan")
	public ResponseEntity<Object> getPlanList() {
		try {
			log.info("여행 계획 리스트 출력 호출");
			List<Plan> list = planService.listPlan();
			
			System.out.println(list.size());
			for (Plan plan : list) {
				log.info(plan.toString());
			}
			
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/plan/{planNo}")
	public ResponseEntity<Object> getPlan(@PathVariable String planNo) {
		try {
			log.info("{}번 여행 계획 출력 호출", planNo);

			Plan plan = planService.getPlan(planNo);
			
			List<PlanDetail> planDetailList = planService.getPlanDetail(planNo);

			planService.updateHit(planNo);
			
			plan.setPlanDetail(planDetailList);
			
			return ResponseEntity.ok().body(plan);

		} catch (Exception e) {
			log.info("error: {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("/plan")
	public ResponseEntity<Object> writeBoard(@RequestBody Plan plan) {
		log.info(plan.toString());
		try {
			planService.createPlan(plan);
			log.info("계획 생성 완료");
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			log.info("계획 생성 실패: {}", e.getMessage());
			
			return ResponseEntity.badRequest().build();
		}
	}
}

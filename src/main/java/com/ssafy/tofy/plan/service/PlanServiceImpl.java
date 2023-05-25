package com.ssafy.tofy.plan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.tofy.plan.dto.Plan;
import com.ssafy.tofy.plan.dto.PlanDetail;
import com.ssafy.tofy.plan.repository.PlanRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class PlanServiceImpl implements PlanService {
	
	private PlanRepository planRepo;
	
	public PlanServiceImpl(PlanRepository planRepo) {
		super();
		this.planRepo = planRepo;
	}

	@Override
	public void createPlan(Plan plan) throws Exception {
		planRepo.createPlan(plan);
		
		List<PlanDetail> planDetailList = plan.getPlanDetail();
		
		if(planDetailList != null && !planDetailList.isEmpty())  {
			planRepo.createPlanDetail(plan);
		}
	}

	@Override
	public void updatePlan(Plan plan) throws Exception {
		planRepo.updatePlan(plan);
	}

	@Override
	public List<Plan> listPlan() throws Exception {
		return planRepo.listPlan();
	}

	@Override
	public Plan getPlan(String planNo) throws Exception {
		log.info("{}번 여행 계획 출력: PlanService" ,planNo);
		return planRepo.getPlan(planNo);
	}

	@Override
	public void deletePlan(int planNo) throws Exception {
		planRepo.deletePlan(planNo);
	}

	@Override
	public void updateHit(String planNo) throws Exception {
		planRepo.updateHit(planNo);
	}

	@Override
	public List<PlanDetail> getPlanDetail(String planNo) throws Exception {
		return planRepo.getPlanDetail(planNo);
	}

	@Override
	public void deletePlanDetail(int planNo) throws Exception {
		planRepo.deletePlan(planNo);
	}
}

package com.ssafy.tofy.plan.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.tofy.plan.dto.Plan;
import com.ssafy.tofy.plan.dto.PlanDetail;
import com.ssafy.tofy.plan.repository.PlanRepository;

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
	}

	@Override
	public void updatePlan(Plan plan) throws Exception {
		planRepo.updatePlan(plan);
	}

	@Override
	public List<Plan> listPlan(Map<String, String> param) throws Exception {
		return planRepo.listPlan(param);
	}

	@Override
	public Plan getPlan(int planNo) throws Exception {
		return planRepo.getPlan(planNo);
	}

	@Override
	public void deletePlan(int planNo) throws Exception {
		planRepo.deletePlan(planNo);
	}

	@Override
	public void updateHit(int planNo) throws Exception {
		planRepo.updateHit(planNo);
	}

	@Override
	public List<PlanDetail> getPlanDetail(int planNo) throws Exception {
		return planRepo.getPlanDetail(planNo);
	}

	@Override
	public void deletePlanDetail(int planNo) throws Exception {
		planRepo.deletePlan(planNo);
	}
}

package com.ssafy.tofy.plan.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.tofy.plan.dto.Plan;
import com.ssafy.tofy.plan.dto.PlanDetail;

@Mapper
public interface PlanRepository {
	// 여행 계획 생성
	public void createPlan(Plan plan) throws Exception;
	// 여행 계획 수정
	public void updatePlan(Plan plan) throws Exception;
	// 여행 계획 리스트 조회
	public List<Plan> listPlan() throws Exception;
	// 여행 계획 1개 조회
	public Plan getPlan(String planNo) throws Exception;
	// 여행 계획 삭제
	public void deletePlan(int planNo) throws Exception;
	// 여행 계획 조회수 증가
	public void updateHit(String planNo) throws Exception;
	// 여행 계획 상세 불러오기
	public List<PlanDetail> getPlanDetail(String planNo)throws Exception;
	// 여행 계획 상세 삭제
	public void deletePlanDetail(int planNo)throws Exception;
	public void createPlanDetail(Plan plan) throws Exception;
}

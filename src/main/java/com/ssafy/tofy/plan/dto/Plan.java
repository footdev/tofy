package com.ssafy.tofy.plan.dto;

import java.util.List;

import com.ssafy.tofy.user.dto.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Plan {
	private int planNo;
	private int userNo;
	private String planTitle;
	private String planCreate;
	private String planStart;
	private String planEnd;
	private int planHit;
	private List<PlanDetail> planDetail;
}

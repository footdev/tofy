package com.ssafy.tofy.plan.dto;

import java.util.List;

import com.ssafy.tofy.user.dto.User;

import lombok.Data;


@Data
public class Plan {
	
	private String planNo;
	private String userNo;
	private String planTitle;
	private String planContent;
	private String planCreate;
	private String planStart;
	private String planEnd;
	private String planHit;
	private User user;
	private List<PlanDetail> planDetail;
}

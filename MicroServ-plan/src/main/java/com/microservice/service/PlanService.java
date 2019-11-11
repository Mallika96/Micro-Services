package com.microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.dto.PlanDTO;
import com.microservice.entity.Plan;
import com.microservice.repository.PlanRepository;



@Service
public class PlanService {


	@Autowired
	PlanRepository planRepo;

	// Fetches all plan details
	public List<PlanDTO> getAllPlans() {

		List<Plan> plans = planRepo.findAll();
		List<PlanDTO> planDTOs = new ArrayList<PlanDTO>();

		for (Plan plan : plans) {
			PlanDTO planDTO = PlanDTO.valueOf(plan);
			planDTOs.add(planDTO);
		}

		
		return planDTOs;
	}

	// Fetch specific plan details
	public PlanDTO getSpecificPlan(int planId) {

		return PlanDTO.valueOf(planRepo.findById(planId).get());
	}

}

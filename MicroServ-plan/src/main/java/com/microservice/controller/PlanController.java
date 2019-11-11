package com.microservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.dto.PlanDTO;
import com.microservice.service.PlanService;

@RestController
@CrossOrigin
public class PlanController {



	@Autowired
	PlanService planService;

	// Fetches all plan details
	@RequestMapping(value = "/plans", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PlanDTO> getAllPlans() {
				return planService.getAllPlans();
	}

	// Fetch plan details of a specific plan
	@GetMapping(value = "/plans/{planId}")
	public PlanDTO getSpecificPlans(@PathVariable Integer planId) {
		
	
		if(planId.equals(0))
		{
			throw new RuntimeException();
		}
		return planService.getSpecificPlan(planId);
	}

}

package com.microservice.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.microservice.dto.PlanDTO;

@FeignClient("PlanMS")
public interface CustPlanFeign {

	@GetMapping(value="/plans/{planId}")
	PlanDTO getSpecificPlans(@PathVariable("planId") int planId);
}



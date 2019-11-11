package com.microservice.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.controller.CustPlanFeign;
import com.microservice.dto.PlanDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CustHystrixCircuit {
	
	 @Autowired RestTemplate template;
	 //@Autowired CustPlanFeign planFeign;
	
	//@HystrixCommand(fallbackMethod="getFriendsFallback")
	public List<Long> getFriends(Long phoneno){

			return template.getForObject("http://FRIENDMS"+"/customers/"+phoneno +"/friends", List.class);
			} 
					
	
		public PlanDTO getSpecificPlans(int  planId){
		return template.getForObject("http://PLANMS"+"/plans/"+planId, PlanDTO.class);
		}
	
		
	
	
	/*
	 * public List<Long> getFriendsFallback(Long phoneno){
	 * System.out.println("+++++++++++InFallback++++++++++++++++++"); return new
	 * ArrayList<Long>();
	 * 
	 * }
	 */
	 
}

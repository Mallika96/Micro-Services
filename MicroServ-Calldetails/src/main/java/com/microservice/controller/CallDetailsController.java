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

import com.microservice.dto.CallDetailsDTO;
import com.microservice.service.CallDetailsService;

@RestController
@CrossOrigin
public class CallDetailsController {


	@Autowired
	CallDetailsService callDetailsService;

	// Fetches call details of a specific customer
	@GetMapping(value = "/customers/{phoneNo}/calldetails")
	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {

	
		return callDetailsService.getCustomerCallDetails(phoneNo);
	}

}

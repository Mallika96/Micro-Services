package com.microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.dto.CallDetailsDTO;
import com.microservice.entity.CallDetails;
import com.microservice.repository.CallDetailsRepository;



@Service
public class CallDetailsService {

	@Autowired
	CallDetailsRepository callDetailsRepo;

	// Fetches call details of a specific customer
	public List<CallDetailsDTO> getCustomerCallDetails(@PathVariable long phoneNo) {

	
		List<CallDetails> callDetails = callDetailsRepo.findByCalledBy(phoneNo);
		List<CallDetailsDTO> callsDTO = new ArrayList<CallDetailsDTO>();

		for (CallDetails call : callDetails) {
			callsDTO.add(CallDetailsDTO.valueOf(call));
		}
		
		return callsDTO;
	}
}

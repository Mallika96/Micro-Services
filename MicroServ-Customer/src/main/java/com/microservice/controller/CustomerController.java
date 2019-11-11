package com.microservice.controller;

import java.net.URI;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.dto.CustomerDTO;
import com.microservice.dto.LoginDTO;
import com.microservice.dto.PlanDTO;
import com.microservice.service.CustHystrixCircuit;
import com.microservice.service.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin
@RefreshScope
public class CustomerController {
	
	//@RibbonClient(name="custribbon")

	@Autowired
	CustomerService custService;
	/*
	 * @Autowired DiscoveryClient client;
	 * 
	 * 
	 * @Value("${friends.uri}") String friendUri;
	 * 
	 * 
	 * @Value("${path.uri}") String pathUri;
	 */
	  
	  @Autowired RestTemplate template;
	  @Autowired CustHystrixCircuit circuit;
	  
	  //@Autowired CustPlanFeign planFeign;
	 
	// Create a new customer
	@PostMapping(value = "/customers")
	public void createCustomer(@RequestBody CustomerDTO custDTO) {
	
		custService.createCustomer(custDTO);
	}

	// Login
	@PostMapping(value = "/login")
	public boolean login(@RequestBody LoginDTO loginDTO) {
	
		return custService.login(loginDTO);
	}

	// Fetches full profile of a specific customer
	@GetMapping(value = "/customers/{phoneNo}")
	@HystrixCommand(fallbackMethod="getCustomerProfileFallback")
	public CustomerDTO getCustomerProfile(@PathVariable Long phoneNo) {
		System.out.println("++++++++++++++InProfile+++++++++++++++++++++");	
		
		/*
		 * List<ServiceInstance> instances=client.getInstances("FRIENDMS");
		 * ServiceInstance instance=instances.get(0); URI friendUri=instance.getUri();
		 * System.out.println(friendUri); List<ServiceInstance>
		 * instances1=client.getInstances("PLANMS"); ServiceInstance
		 * instance2=instances1.get(0); URI pathUri=instance2.getUri();
		 * System.out.println(pathUri);
		 */
		
		
		CustomerDTO custDTO=custService.getCustomerProfile(phoneNo);
		PlanDTO planDTO=circuit.getSpecificPlans(custDTO.getCurrentPlan().getPlanId());
				
		custDTO.setCurrentPlan(planDTO);
		//template.getForObject("http://PLANMS"+"/plans/"+custDTO.getCurrentPlan().getPlanId(), PlanDTO.class);
	
		//List<Long> friends=template.getForObject("http://custribbon/customers/"+phoneNo+"/friends", List.class);
		
		//List<Long> friends=template.getForObject("http://FRIENDMS"+"/customers/"+phoneNo+"/friends", List.class);
		List<Long> friends=circuit.getFriends(phoneNo);
		custDTO.setFriendAndFamily(friends);
		return custDTO;
	}

	public CustomerDTO getCustomerProfileFallback(Long phoneNo) {
		System.out.println("+++++++++++InFallback++++++++++++++++++");
		return new CustomerDTO();	}


}

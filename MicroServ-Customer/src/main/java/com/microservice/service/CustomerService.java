package  com.microservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.dto.CustomerDTO;
import com.microservice.dto.LoginDTO;
import com.microservice.entity.Customer;
import com.microservice.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository custRepo;
	
	public void createCustomer(CustomerDTO custDTO) {
			Customer cust = custDTO.createEntity();
		custRepo.save(cust);
	}

	// Login
	
	public boolean login(LoginDTO loginDTO) {
		
		Customer cust = custRepo.findById(loginDTO.getPhoneNo()).get();
		if (cust != null && cust.getPassword().equals(loginDTO.getPassword())) {
			return true;
		}
		return false;
	}

	// Fetches full profile of a specific customer
	
	public CustomerDTO getCustomerProfile( Long phoneNo) {

		Customer cust = custRepo.findById(phoneNo).get();
		CustomerDTO custDTO = CustomerDTO.valueOf(cust);

		return custDTO;
	}


}

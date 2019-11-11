package  com.microservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.entity.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long> {

	

}

package  com.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.entity.Plan;



public interface PlanRepository extends JpaRepository<Plan, Integer> {

}

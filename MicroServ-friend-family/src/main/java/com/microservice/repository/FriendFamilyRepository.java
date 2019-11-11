package  com.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.entity.FriendFamily;


public interface FriendFamilyRepository extends JpaRepository<FriendFamily, Integer> {

	List<FriendFamily> getByPhoneNo(Long phoneNo);
}

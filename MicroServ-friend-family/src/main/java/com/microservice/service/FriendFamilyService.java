package  com.microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.dto.FriendFamilyDTO;
import com.microservice.entity.FriendFamily;
import com.microservice.repository.FriendFamilyRepository;



@Service
public class FriendFamilyService {
	
	@Autowired
	FriendFamilyRepository friendRepo;

	// Create Friend Family
	
	public void saveFriend(Long phoneNo, FriendFamilyDTO friendDTO) {
	
		friendDTO.setPhoneNo(phoneNo);
		FriendFamily friend = friendDTO.createFriend();
		friendRepo.save(friend);
	}
	
	// Get friend and family phone number list of a given customer
	public List<Long> getSpecificFriends(Long phoneNo){

		List<Long> friendList= new ArrayList<Long>();
		List<FriendFamily> friends=friendRepo.getByPhoneNo(phoneNo);
		for (FriendFamily friendFamily : friends) {
			friendList.add(friendFamily.getFriendAndFamily());
		}
		
		return friendList;
	}

}

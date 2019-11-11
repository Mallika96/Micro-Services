package  com.microservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.dto.FriendFamilyDTO;
import com.microservice.service.FriendFamilyService;


@RestController
@CrossOrigin
public class FriendFamilyController {

	
	@Autowired
	FriendFamilyService friendService;

	
	@PostMapping(value = "/customers/{phoneNo}/friends")
	public void saveFriend(@PathVariable Long phoneNo, @RequestBody FriendFamilyDTO friendDTO) {
		
		friendService.saveFriend(phoneNo, friendDTO);
	}


	@GetMapping(value = "/customers/{phoneNo}/friends")
	public List<Long> getSpecificFriends(@PathVariable Long phoneNo) {
					System.out.println("fetching data");		
		return friendService.getSpecificFriends(phoneNo);
	}

}

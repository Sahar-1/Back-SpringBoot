package Esprit.PiDev.RestController;

import java.text.ParseException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.Bus;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Bus_Repository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.Service.Appointment_Service;
import Esprit.PiDev.Service.Bus_Service;
import Esprit.PiDev.Service.Bus_Service;
import Esprit.PiDev.Service.Session_UserDetails;
import Esprit.PiDev.Service.User_Role_Service;
import Esprit.PiDev.Service.User_Service;




@RestController
public class Bus_Controller {
	@Autowired
	Bus_Repository Bus_Repository;
	@Autowired
	User_Repository user_Repository;

	@Autowired
	User_Repository ur1;
	@Autowired
	User_Service us;
	@Autowired
	User_Role_Service ur;

	@Autowired
	Role_Repository role_Repository;

	@Autowired
	Interface_User_Service userService;

	@Autowired
	Bus_Service BusService;
	
	@Autowired
	Bus_Service busService;
	@Autowired
	Appointment_Service appointmentService;

	// ****************************getAllBus_ByBus************************************//
	// @Secured(value ={"ROLE_ADMIN"})
	@GetMapping("/getAllBus_ByBus/{garden_id}")
	public ResponseEntity<?> getAllBus_ByBus(Authentication auth,@PathVariable("garden_id") Long garden_id) {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return busService.getAllBus_ByGarden(userDetails.getId(), garden_id);
	}
	// ****************************getAllBus_ByBus************************************//

	
	
	
	// ****************************ajouterBus_byGarden************************************//
	// @Secured(value ={"ROLE_ADMIN"})
	@PostMapping("/ajouterBus_byGarden/{garden_id}")
	public ResponseEntity<?> ajouterBus_byGarden(Authentication auth, @RequestBody Bus Bus,@PathVariable("garden_id") Long garden_id) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return busService.ajouterBus_byGarden(userDetails.getId(), Bus,garden_id);
	}
	// ****************************ajouterBus************************************//

	
	
	
	// ****************************DeleteBus************************************//
	// @Secured(value ={"ROLE_ADMIN"})
	@DeleteMapping("/DeleteBus/{Bus_id}")
	public ResponseEntity<?> DeleteBus(Authentication auth, @PathVariable int Bus_id) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return busService.DeleteBus(userDetails.getId(), Bus_id);
	}
	// ****************************DeleteBus************************************//

	
	
	
	
	
	// ****************************UpdateBus************************************//
	// @Secured(value ={"ROLE_ADMIN"})
	@PutMapping("/UpdateBus/{Bus_id}")
	public ResponseEntity<?> UpdateBus(Authentication auth, @PathVariable int Bus_id,@RequestBody Bus Bus) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return BusService.UpdateBus(userDetails.getId(), Bus_id,Bus);
	}
	// ****************************UpdateBus************************************//
	
	
	

	
	
	
	

	
	
	
	


	


	

	
	
	
		

}

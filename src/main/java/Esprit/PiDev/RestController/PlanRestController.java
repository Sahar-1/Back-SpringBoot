package Esprit.PiDev.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Claim;
import Esprit.PiDev.Entity.Plan;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.InterfaceService.Plan_Service;

import Esprit.PiDev.Repository.Plan_Repository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.Service.Session_UserDetails;
import Esprit.PiDev.Service.User_Role_Service;
import Esprit.PiDev.Service.User_Service;
@RestController
public class PlanRestController {
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
	Plan_Repository plan_rep;
	@Autowired
	Plan_Service plan_serv;
	
	@PostMapping("/addplan")
	public ResponseEntity<?> add_Plan( org.springframework.security.core.Authentication auth, @RequestBody Plan plan) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return plan_serv.Add_Plan( userDetails.getId(), plan);
	}
}

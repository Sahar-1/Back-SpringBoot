package Esprit.PiDev.RestController;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Claim;
 
 import Esprit.PiDev.InterfaceService.Claim_Service;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Claim_Repository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.Service.Session_UserDetails;
import Esprit.PiDev.Service.User_Role_Service;
import Esprit.PiDev.Service.User_Service;



@RestController
//@RequestMapping("/Claim")
public class ClaimRestController {

	
	
	@Autowired
	Claim_Repository cl_rep;
	@Autowired
	Claim_Service clm_serv;
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


	@PostMapping("/addclaim")
	public ResponseEntity<?> addClaim( org.springframework.security.core.Authentication auth, @RequestBody Claim claim) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return clm_serv.Add_Claim(userDetails.getId(), claim);
	}
	
	@PostMapping("/affectation_Claim_2/{garden_id}")
	public ResponseEntity<?> affectation_Claim_2( org.springframework.security.core.Authentication auth, @RequestBody Claim claim,@PathVariable("garden_id") Long garden_id) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return clm_serv.affectation_Claim_2(userDetails.getId(), claim,garden_id);
	}
	@GetMapping("/Retrieve_All_Claims/{id_garden}")
	public ResponseEntity<?> retrieve_all_claims(org.springframework.security.core.Authentication auth, @RequestBody Claim claim,@PathVariable Long id_garden)
  {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		
		return clm_serv.Retrieve_All_Claims(userDetails.getId(), id_garden);

		
  }
	@DeleteMapping("/DeleteClaim/{claim_id}")
	public ResponseEntity<?> DeleteClaim(org.springframework.security.core.Authentication auth, @PathVariable int claim_id) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return clm_serv.Delete_Claim(userDetails.getId(), claim_id);
	}
	@PutMapping("/modify-Claim/{claim_id}")
	public ResponseEntity<?> UpdateContract(org.springframework.security.core.Authentication auth, @PathVariable("claim_id") int claim_id, @RequestBody Claim claim) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return clm_serv.Validate_Claim(userDetails.getId(), claim_id, claim); 
	}
 
	
	@GetMapping("/investisment/{id_garden}")
	public ResponseEntity<?>investisment (org.springframework.security.core.Authentication auth,@PathVariable("id_garden") Long id_garden)
	{

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	return 	clm_serv.investisment(userDetails.getId(), id_garden);
	}
	
}

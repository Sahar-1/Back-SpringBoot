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
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.Service.Appointment_Service;
import Esprit.PiDev.Service.Garden_Service;
import Esprit.PiDev.Service.Session_UserDetails;
import Esprit.PiDev.Service.User_Role_Service;
import Esprit.PiDev.Service.User_Service;

@RestController
public class Garden_Controller {

	@Autowired
	Garden_Repository garden_Repository;
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
	Garden_Service gardenService;
	@Autowired
	Appointment_Service appointmentService;

	// ****************************getAllGarden************************************//
	// @Secured(value ={"ROLE_ADMIN"})
	@GetMapping("/getAllGarden")
	public ResponseEntity<?> getAllGarden(Authentication auth) {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return gardenService.getAllGarden(userDetails.getId());

	}
	// ****************************getAllGarden************************************//

	
	
	
	// ****************************ajouterGarden************************************//
	// @Secured(value ={"ROLE_ADMIN"})
	@PostMapping("/ajouterGarden")
	public ResponseEntity<?> ajouterGarden(Authentication auth, @RequestBody Garden garden) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return gardenService.ajouterGarden(userDetails.getId(), garden);
	}
	// ****************************ajouterGarden************************************//

	
	
	
	// ****************************DeleteGarden************************************//
	// @Secured(value ={"ROLE_ADMIN"})
	@DeleteMapping("/DeleteGarden/{garden_id}")
	public ResponseEntity<?> DeleteGarden(Authentication auth, @PathVariable Long garden_id) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return gardenService.DeleteGarden(userDetails.getId(), garden_id);
	}
	// ****************************DeleteGarden************************************//

	
	
	
	
	
	// ****************************UpdateGarden************************************//
	// @Secured(value ={"ROLE_ADMIN"})
	@PutMapping("/UpdateGarden/{garden_id}")
	public ResponseEntity<?> UpdateGarden(Authentication auth, @PathVariable Long garden_id,@RequestBody Garden garden) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return gardenService.UpdateGarden(userDetails.getId(), garden_id,garden);
	}
	// ****************************UpdateGarden************************************//
	
	
	// ****************************ajouterparentenfant************************************//

	@PostMapping("/ajouterparentenfant")
	@ResponseBody
	public HashSet<Dbo_User> ajouteruserGarten(Authentication auth, @RequestBody HashSet<Dbo_User> users)
			throws ParseException {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return gardenService.ajouterparentenfant(userDetails.getId(), users);

	}
	// ****************************ajouterparentenfant************************************//
	
	
	
	
	
	

	// ****************************affecter_enfant_parent_jardin************************************//

	@PostMapping("/affecter_enfant_parent_jardin/{garden_id}")
	@ResponseBody
	public ResponseEntity<?> affecter_enfant_parent_jardin(Authentication auth, @RequestBody HashSet<Dbo_User> users,
			@PathVariable("garden_id") Long garden_id) throws ParseException {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return gardenService.affecter_enfant_parent_jardin(userDetails.getId(), users, garden_id);
	}
	// ****************************affecter_enfant_parent_jardin************************************//
	
	
	

	// ****************************afficher_enfant_byparent************************************//
	@GetMapping("/afficher_enfant_byparent")
	public ResponseEntity<?> afficher_enfant_byparent(Authentication auth) {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return gardenService.afficher_enfant_byparent(userDetails.getId());
	}
	// ****************************afficher_enfant_byparent************************************//
	
	

	// ****************************select_parent_by_Garden********************************//
	@Secured(value = { "ROLE_ADMIN" })
	@GetMapping("/select_parent_by_Garden/{garden_id}")
	public ResponseEntity<?> select_parent_by_Garden(Authentication auth, @PathVariable("garden_id") Long garden_id) {
		return gardenService.select_parent_by_Garden(garden_id);
	}
	// ****************************select_parent_by_Garden********************************//

	
	
	// ****************************select_enfant_parent_by_Garden********************************//
	@Secured(value = { "ROLE_ADMIN" })
	@GetMapping("/select_enfant_parent_by_Garden/{garden_id}/{parent_id}")
	public ResponseEntity<?> select_enfant_parent_by_Garden(Authentication auth,
			@PathVariable("garden_id") Long garden_id, @PathVariable("parent_id") long parent_id) {
		return gardenService.select_enfant_parent_by_Garden(garden_id, parent_id);
	}
	// ****************************select_enfant_parent_by_Garden********************************//
	
	
	// ****************************select_enfant_by_Garden********************************//
		@Secured(value = { "ROLE_ADMIN" })
		@GetMapping("/select_enfant_by_Garden/{garden_id}")
		public ResponseEntity<?> select_enfant_by_Garden(Authentication auth,
				@PathVariable("garden_id") Long garden_id) {
			return gardenService.select_enfant_by_Garden(garden_id);
		}
		// ****************************select_enfant_by_Garden********************************//
		
		
		
		
		
		
		
	
	
	

}

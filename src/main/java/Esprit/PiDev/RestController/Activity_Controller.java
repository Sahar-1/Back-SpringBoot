package Esprit.PiDev.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Activity;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.Service.Activity_Service;
import Esprit.PiDev.Service.Session_UserDetails;

@RestController
public class Activity_Controller {
	
	@Autowired 
	Activity_Service activityService;
	
	// ****************************getAllAcivity************************************//
		// @Secured(value ={"ROLE_ADMIN"})
		@GetMapping("/getAllAcivity")
		public ResponseEntity<?> getAllAcivity(Authentication auth) {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return activityService.GetAllActivity(userDetails.getId());

		}
		// ****************************getAllAcivity************************************//

		
		
		
		// ****************************AjouterActivity************************************//
		// @Secured(value ={"ROLE_ADMIN"})
		@PostMapping("/AjouterActivity")
		public ResponseEntity<?> AjouterActivity(Authentication auth, @RequestBody Activity activity) {

			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return activityService.AjouterActivity(userDetails.getId(), activity);
		}
		// ****************************AjouterActivity************************************//

		
		
		
		// ****************************DeleteActivity************************************//
		// @Secured(value ={"ROLE_ADMIN"})
		@DeleteMapping("/DeleteActivity/{activity_id}")
		public ResponseEntity<?> DeleteGarden(Authentication auth, @PathVariable int activity_id) {

			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return activityService.DeleteActivity(userDetails.getId(),activity_id);
		}
		// ****************************DeleteActivity************************************//

		
		
		
		
		
		// ****************************UpdateActivity************************************//
		// @Secured(value ={"ROLE_ADMIN"})
		@PutMapping("/UpdateActivity/{activity_id}")
		public ResponseEntity<?> UpdateActivity(Authentication auth, @PathVariable int activity_id,@RequestBody Activity  activity) {

			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return activityService.UpdateActivity(userDetails.getId(), activity_id, activity);
		}
		
		
		
		// ****************************UpdateActivity************************************//
		
		

		// ****************************SearchActivity************************************//
		// @Secured(value ={"ROLE_ADMIN"})
		@GetMapping("/SearchActivity/{search}")
		public ResponseEntity<?> SearchActivity(Authentication auth, @PathVariable String search) {

			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return activityService.SearchActivity(userDetails.getId(), search);
		}
		// ****************************UpdateActivity************************************//
		
		
	
		
		

}

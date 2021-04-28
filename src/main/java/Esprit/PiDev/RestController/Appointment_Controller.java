package Esprit.PiDev.RestController;

import java.text.ParseException;

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

import Esprit.PiDev.Entity.Appointment;
import Esprit.PiDev.Entity.Historique;
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
public class Appointment_Controller {

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
	

	// ****************************ajouter_Parent_rendezVous********************************//
//	@Secured(value = {"PARENT"})
	@PostMapping("/ajouter_Parent_rendezVous/{garden_id}")
	public ResponseEntity<?> ajouter_Parent_rendezVous(Authentication auth,@PathVariable("garden_id") Long garden_id,@RequestBody Appointment appointment) throws ParseException {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return appointmentService.ajouter_Parent_rendezVous(userDetails.getId(),garden_id,appointment);
	}
			// ****************************ajouter_Parent_rendezVous********************************//
	
	
	

	// ****************************ajouter_admin_rendezVous********************************//
//	@Secured(value = {"PARENT"})
	@PostMapping("/ajouter_admin_rendezVous/{garden_id}/{parent_id}")
	public ResponseEntity<?> ajouter_admin_rendezVous(Authentication auth,@PathVariable("garden_id") Long garden_id,@PathVariable("parent_id") int parent_id,@RequestBody Appointment appointment) throws ParseException {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return appointmentService.ajouter_admin_rendezVous(userDetails.getId(),parent_id,garden_id,appointment);
	}
			// ****************************ajouter_admin_rendezVous********************************//
			
		
	
	
	
		
		//****************************************getall_appointment_bygarden******************************************************//
		@GetMapping("/getall_appointment_bygarden/{garden_id}")
		public ResponseEntity<?> getall_appointment_bygarden(Authentication auth,@PathVariable("garden_id") Long garden_id) throws ParseException {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return appointmentService.getall_appointment_bygarden(userDetails.getId(),garden_id);
		
		}
		
		//****************************************getall_appointment_bygarden******************************************************//

		
		
		//****************************************delete_appointment******************************************************//

		@DeleteMapping("/delete_appointment/{appointment_id}")
		public ResponseEntity<?> delete_appointment(Authentication auth,@PathVariable("appointment_id") int appointment_id) throws ParseException {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return appointmentService.delete_appointment(userDetails.getId(),appointment_id);
		
		}
		//****************************************delete_appointment******************************************************//
		
		
		
		// ****************************update_appointment************************************//
		// @Secured(value ={"ROLE_ADMIN"})
		@PutMapping("/update_appointment/{appointment_id}")
		public ResponseEntity<?> update_appointment(Authentication auth, @PathVariable int appointment_id,@RequestBody Appointment  appointment) {

			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return appointmentService.update_appointment(userDetails.getId(), appointment_id, appointment);
		}
		
		
		
		// ****************************update_appointment************************************//

		

		// ****************************searchappointment************************************//
		// @Secured(value ={"ROLE_ADMIN"})
		@GetMapping("/searchappointment/{search}")
		public ResponseEntity<?> searchappointment(Authentication auth, @PathVariable String search) {

			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return appointmentService.searchappointment(userDetails.getId(), search);
		}
		// ****************************searchappointment************************************//
		
		
		
		
		
		
		
		
		
		
		// ****************************getallappointment_year********************************//

				@GetMapping("/getallappointment_year/{annee}")
				public ResponseEntity<?> Accept_appointment(Authentication auth,@PathVariable("annee") int annee) throws ParseException {
					SecurityContextHolder.getContext().setAuthentication(auth);
					Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
					return appointmentService.getallappointment_year(userDetails.getId(),annee);
				}
				
				// ****************************getallappointment_year********************************//
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	

	

	

	
		//****************************************accepte_appointment******************************************************//
		
		@PostMapping("/accepte_appointment/{appointment_id}")
		public ResponseEntity<?> ajouter_admin_rendezVous(Authentication auth,@PathVariable("appointment_id") int appointment_id) throws ParseException {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return appointmentService.accepte_appointment(userDetails.getId(),appointment_id);
		
		}
		
		//****************************************accepte_appointment******************************************************//

		
		//****************************************refut_appointment******************************************************//
		
			@PostMapping("/refut_appointment/{appointment_id}")
			public ResponseEntity<?> refut_appointment(Authentication auth,@PathVariable("appointment_id") int appointment_id) throws ParseException {
				SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
				return appointmentService.refut_appointment(userDetails.getId(),appointment_id);
			
			}
			
			//****************************************refut_appointment******************************************************//
	

			// ****************************getallappointment_status_1********************************//

			@GetMapping("/getallappointment_status_1")
			public ResponseEntity<?> getallappointment_status_1(Authentication auth) throws ParseException {
				SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
				return appointmentService.getallappointment_status_1(userDetails.getId());
			}
			
			// ****************************getallappointment_status_1********************************//
			
			
			//****************************************ajouter_note_appointment_medecin******************************************************//
			@PostMapping("/ajouter_note_appointment_medecin/{appointment_id}")
			public ResponseEntity<?> ajouter_note_appointment_medecin(Authentication auth,@PathVariable("appointment_id") int appointment_id,@RequestBody Historique  historique) throws ParseException {
				SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
				return appointmentService.ajouter_note_appointment_medecin(userDetails.getId(),appointment_id, historique);
			}
			//****************************************ajouter_note_appointment_medecin******************************************************//
			
			
			//****************************************lister_appointment_historique_byparent******************************************************//
			@GetMapping("/lister_appointment_historique_byparent/{id_parent}")
			public ResponseEntity<?> lister_appointment_historique_byparent(Authentication auth,@PathVariable("id_parent") long id_parent) throws ParseException {
				SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
				return appointmentService.lister_appointment_historique_byparent(userDetails.getId(),id_parent);
			}
			//****************************************ajouter_note_appointment_medecin******************************************************//

	

			//****************************************lister_date_disponible_bygarden******************************************************//
			@GetMapping("/lister_date_disponible_bygarden/{id_garden}/{date}")
			public ResponseEntity<?> lister_date_disponible_bygarden(Authentication auth,@PathVariable("id_garden") Long id_garden,@PathVariable("date") String date) throws ParseException {
				SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
				return appointmentService.lister_date_disponible_bygarden(userDetails.getId(),id_garden,date);
			}
			//****************************************lister_date_disponible_bygarden******************************************************//

	
	
}

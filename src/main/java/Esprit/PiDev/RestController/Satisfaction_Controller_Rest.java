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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Review;
import Esprit.PiDev.Entity.Satisfaction;
import Esprit.PiDev.Repository.Question_Satisfaction_Repository;
import Esprit.PiDev.Service.Satisfaction_Service;
import Esprit.PiDev.Service.Session_UserDetails;

@RestController
@RequestMapping("/Satisfaction")
public class Satisfaction_Controller_Rest {

	@Autowired
	Satisfaction_Service satisfactionservice;
	@Autowired
	Question_Satisfaction_Repository rep_quest;

	
	
	
	
	
	@GetMapping("/retrieve-all-satisfactions")
	public List<Satisfaction> getSatisfactions() {
		List<Satisfaction> list = satisfactionservice.retrieveAllSatisfactions();
		return list;
	}

	@GetMapping("/retrieve-satisfaction/{satisfaction-id}")
	public void retrieveSatisfaction(@PathVariable("satisfaction-id") Long id) {
		satisfactionservice.findSatisfactionById(id);
	}

	@PostMapping("/add-satisfaction/{id-user}")
	@ResponseBody
	public ResponseEntity<?> addSatisfaction(Authentication auth, @RequestBody Satisfaction satisfaction,
			@PathVariable("id-user") Long iduser) {

		return satisfactionservice.addSatisfaction(satisfaction, iduser);

	}

	@DeleteMapping("/remove-satisfaction/{satisfaction-id}")
	public void removeSatisfaction(@PathVariable("satisfaction-id") Long id) {
		satisfactionservice.deleteSatisfactionById(id);
	}

	@PutMapping("/modify-satisfaction/{iduser}/{idsat}")
	public void modifySatisfaction(@RequestBody Satisfaction satisfaction, @PathVariable("iduser") Long iduser,
			@PathVariable("idsat") Long idsat) {
		satisfactionservice.saveOrUpdate(satisfaction, iduser, idsat);
	}

	@PutMapping("/Affecter_Answer_Question_Satisfaction/{name}")
	public ResponseEntity<?> Affecter_Answer_Question_Satisfaction(Authentication auth, @PathVariable Long idsat,
			@RequestBody List<Review> answers) {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return satisfactionservice.Affecter_Answer_Question_Satisfaction(userDetails.getId(), idsat, answers);
	}

	@GetMapping("/getAllQuestionsbySatisfaction/{idsat}")
	public ResponseEntity<?> getAllQuestionsbySatisfactions(Authentication auth, @PathVariable Long idsat) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return satisfactionservice.getAllQuestionsbySatisfaction(userDetails.getId(), idsat);
	}
         
	@GetMapping("/StatistiqueAnswer_QuetionSatisfactionbUSer/{idusersat}/{namesat}")
	public ResponseEntity<?> StatistiqueAnswer_QuetionSatisfactionbUSer(Authentication auth, @PathVariable Long idusersat,@PathVariable String namesat)
	   {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return satisfactionservice.StatistiqueAnswer_QuetionSatisfactionbUSer(userDetails.getId(), idusersat, namesat);
		}


}

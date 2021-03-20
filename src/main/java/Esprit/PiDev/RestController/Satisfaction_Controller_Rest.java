package Esprit.PiDev.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import Esprit.PiDev.Entity.Satisfaction;
import Esprit.PiDev.Repository.Question_Satisfaction_Repository;
import Esprit.PiDev.Service.Satisfaction_Service;

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
	   
	   
	   @PostMapping ("/add-satisfaction/{id-user}")
	   @ResponseBody
	   public ResponseEntity<?> addSatisfaction(Authentication auth, @RequestBody Satisfaction satisfaction,@PathVariable("id-user") Long iduser  ) {
		

		   
		   return   satisfactionservice.addSatisfaction(satisfaction, iduser);
        
	   }

	   @DeleteMapping("/remove-satisfaction/{satisfaction-id}") 
	   public void removeSatisfaction(@PathVariable("satisfaction-id") Long id)
	   {
		   satisfactionservice.deleteSatisfactionById(id);
	   } 

	   @PutMapping("/modify-satisfaction/{iduser}/{idsat}") 
	   public void modifySatisfaction(@RequestBody Satisfaction satisfaction,@PathVariable("iduser") Long iduser,@PathVariable("idsat") Long idsat) {
		   satisfactionservice.saveOrUpdate(satisfaction, iduser, idsat);
	   }
	
	
	
	
	
	
	
	
	
	
}

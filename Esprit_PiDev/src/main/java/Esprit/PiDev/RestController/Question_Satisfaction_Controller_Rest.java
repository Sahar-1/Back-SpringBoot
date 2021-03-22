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
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Question_Satisfaction;
import Esprit.PiDev.Entity.Review;
import Esprit.PiDev.Service.Question_Satisfaction_Service;
import Esprit.PiDev.Service.Session_UserDetails;

@RestController
@RequestMapping("/question")
public class Question_Satisfaction_Controller_Rest {
	@Autowired
	Question_Satisfaction_Service questionservice;
	
	   @GetMapping("/retrieve-all-questions")
	    List<Question_Satisfaction> Question(Authentication auth) {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	          List<Question_Satisfaction> list = questionservice.retrieveAllQuestions();
	          return list;
	   } 
	
	   @GetMapping("/retrieve-question/{question-id}")
	   public void retrieveQuestion(@PathVariable("question-id") Long id) {
		   questionservice.findQuestionById(id);
	   } 
	   
	   
	   @PostMapping("/add-question")
	   public ResponseEntity<?>  addReaction(Authentication auth,@RequestBody Question_Satisfaction question) {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		   return  questionservice.addQuestion(question); 
	   }

	   @DeleteMapping("/remove-question/{question-id}") 
	public void  removeReaction(Authentication auth,@PathVariable("question-id") Long id)
	   {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		   questionservice.deleteQuestionById(id);
	   } 

	   @PutMapping("/modify-question/{rev}") 
		public void   modifyReaction(@RequestBody Question_Satisfaction question,@PathVariable("rev") Review rev) {
		   questionservice.saveOrUpdate(question,rev);
	   }
	
	
	
}

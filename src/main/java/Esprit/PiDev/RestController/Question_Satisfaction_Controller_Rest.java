package Esprit.PiDev.RestController;

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
import Esprit.PiDev.Service.Question_Satisfaction_Service;
import Esprit.PiDev.Service.Session_UserDetails;

@RestController
@RequestMapping("/question")
public class Question_Satisfaction_Controller_Rest {
	@Autowired
	private	Question_Satisfaction_Service question_Service;

	// ****************************getAllQuestion************************************//
	// @Secured(value ={"ROLE_ADMIN"})
	@GetMapping("/getAllQuestion")
	public ResponseEntity<?> getAllQuestions(Authentication auth) {
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return question_Service.getAllQuestions(userDetails.getId());

	}

	

	@PostMapping("/addQuestion")
	public ResponseEntity<?> AddQuestion(Authentication auth, @RequestBody Question_Satisfaction question) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return question_Service.addQuestion(userDetails.getId(), question);
	}

	@DeleteMapping("/deleteQuestionById/{question_id}")
	public ResponseEntity<?> DeleteQuestionById(Authentication auth, @PathVariable Long question_id) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return question_Service.deleteQuestionById(userDetails.getId(), question_id);
	}

	@PutMapping("/Updatequestion/{question_id}")
	public ResponseEntity<?> Updatequestion(Authentication auth, @PathVariable Long question_id,
			@RequestBody Question_Satisfaction question) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return question_Service.UpdateQuestion(userDetails.getId(), question_id, question);
	}

	
}

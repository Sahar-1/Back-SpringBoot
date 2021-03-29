package Esprit.PiDev.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Answer_Satisfaction;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Question_Satisfaction;
import Esprit.PiDev.Entity.Review;
import Esprit.PiDev.Entity.Satisfaction;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Answer_Repository;
import Esprit.PiDev.Repository.Question_Satisfaction_Repository;
import Esprit.PiDev.Repository.Satisfaction_Repository;
import Esprit.PiDev.Repository.User_Repository;
@Service
public class Question_Satisfaction_Service_Impl implements Question_Satisfaction_Service {
	
	

	@Autowired
	private Question_Satisfaction_Repository question_Repository;

	@Autowired
	private User_Repository ur1;
	@Autowired
	private 	User_Service us;
	@Autowired
	private User_Role_Service ur;
	
	@Autowired
	Satisfaction_Repository sat_rep;
	
	@Autowired
	Answer_Repository answer_Repository;
	@Override
	public ResponseEntity<?> getAllQuestions(Long user_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

		{
			return ResponseEntity.ok(new MessageResponse("" + question_Repository.findAll()));

		}
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

		}

	}

	
	
	
	@Override
	public ResponseEntity<?> addQuestion(Long user_id, Question_Satisfaction question) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			List<Question_Satisfaction> quess=new ArrayList<>();
			question_Repository.findAll().forEach(quess::add);
			if (!quess.contains(question)) 
			{
				question_Repository.save(question);
				return ResponseEntity.ok(new MessageResponse("question est bien enregistrer"));
			}

			else {

				return ResponseEntity.ok(new MessageResponse("question existe "));

			}

		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}

		@Override
	public ResponseEntity<?> deleteQuestionById(long user_id, Long question_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			Question_Satisfaction question = question_Repository.findById(question_id).orElse(null);
			if (question != null) {
				question_Repository.deleteById(question_id);
				return ResponseEntity.ok(new MessageResponse("question est supprimer "));
			} else {
				return ResponseEntity.ok(new MessageResponse("question n'existe pas"));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}

	
	
	@Override
	public ResponseEntity<?> UpdateQuestion(long user_id, Long question_id, Question_Satisfaction  question) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			Question_Satisfaction question_To_Update = question_Repository.findById(question_id).orElse(null);
			if (question_To_Update != null) {

				question_To_Update.setQuestion_Sat(question.getQuestion_Sat());

				question_Repository.save(question_To_Update);
				return ResponseEntity.ok(new MessageResponse("question est bien modifier "));
			} else {
				return ResponseEntity.ok(new MessageResponse("question n'existe pas"));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}
	

	
	

	
	
	

	
	


	
}

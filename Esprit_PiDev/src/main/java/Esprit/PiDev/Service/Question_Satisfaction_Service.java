package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Question_Satisfaction;
import Esprit.PiDev.Entity.Review;

public interface Question_Satisfaction_Service {
	 ResponseEntity<?> addQuestion(Question_Satisfaction question);

	List<Question_Satisfaction> retrieveAllQuestions();

	void saveOrUpdate(Question_Satisfaction question ,Review rev);

	Question_Satisfaction findQuestionById(Long id);

	void deleteQuestionById(Long id);
	
	Question_Satisfaction retrieveQuestion(Question_Satisfaction question);

	void deleteQuestion(Question_Satisfaction question);
}

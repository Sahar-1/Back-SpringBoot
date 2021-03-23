package Esprit.PiDev.Service;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Question_Satisfaction;

public interface Question_Satisfaction_Service {
//	 ResponseEntity<?> addQuestion(Question_Satisfaction question);
//
//	List<Question_Satisfaction> retrieveAllQuestions();
//
//	void saveOrUpdate(Question_Satisfaction question ,Review rev);
//
//	Question_Satisfaction findQuestionById(Long id);
//
//	void deleteQuestionById(Long id);
//	
//	Question_Satisfaction retrieveQuestion(Question_Satisfaction question);
//
//	void deleteQuestion(Question_Satisfaction question);
	
	
	public ResponseEntity<?> getAllQuestions(Long user_id) ;
	public ResponseEntity<?> addQuestion(Long user_id, Question_Satisfaction question,Long idsat) ;
	public ResponseEntity<?> deleteQuestionById(long user_id, Long question_id);
	public ResponseEntity<?> UpdateQuestion(long user_id, Long question_id, Question_Satisfaction  question);

}

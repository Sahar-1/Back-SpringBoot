package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Question_Satisfaction;
import Esprit.PiDev.Entity.Review;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Question_Satisfaction_Repository;
@Service
public class Question_Satisfaction_Service_Impl implements Question_Satisfaction_Service {
	
	
	
	@Autowired
	Question_Satisfaction_Repository quest_rep;
	
	
	
@Override

public ResponseEntity<?>  addQuestion(Question_Satisfaction question) {
	// TODO Auto-generated method stub
	quest_rep.save(question);
	 return ResponseEntity.ok(new MessageResponse("" + quest_rep.save(question)));

}
@Override
	public void deleteQuestion(Question_Satisfaction question) {
		// TODO Auto-generated method stub
	quest_rep.delete(question);
	}
@Override
	public void deleteQuestionById(Long id) {
		// TODO Auto-generated method stub
	quest_rep.deleteById(id);
	}
@Override
	public Question_Satisfaction findQuestionById(Long id) {
		// TODO Auto-generated method stub
		return quest_rep.findById(id).get();
	}
@Override
	public List<Question_Satisfaction> retrieveAllQuestions() {
		// TODO Auto-generated method stub
		return (List<Question_Satisfaction>)quest_rep.findAll();
	}
	@Override
		public Question_Satisfaction retrieveQuestion(Question_Satisfaction question) {
			// TODO Auto-generated method stub
			return quest_rep.findById(question.getId()).get();
		}
	@Override
		public void saveOrUpdate(Question_Satisfaction question ,Review rev) {
			// TODO Auto-generated method stub
		Question_Satisfaction quess =quest_rep.findById(question.getId()).get();
		 quess.setReviews(rev);
		quest_rep.save(quess);
		}
}

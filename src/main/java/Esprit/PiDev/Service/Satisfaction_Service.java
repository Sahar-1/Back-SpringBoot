package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Answer_Satisfaction;
import Esprit.PiDev.Entity.Review;
import Esprit.PiDev.Entity.Satisfaction;

public interface Satisfaction_Service {
	
	public ResponseEntity<?> addSatisfaction(Satisfaction satisfaction, Long iduser);
	public ResponseEntity<?>  AffecterQuetions_Satisfaction_Satisfaction_User(Satisfaction satisfaction, Long iduser);

	List<Satisfaction> retrieveAllSatisfactions();

	public ResponseEntity<?>  saveOrUpdate(Satisfaction satisfaction,Long iduser,Long sat);

	Satisfaction findSatisfactionById(Long id);

	public ResponseEntity<?>  deleteSatisfactionById(Long id);
	
	Satisfaction retrieveSatisfaction(Satisfaction satisfaction);
	public ResponseEntity<?> StatistiqueAnswer_QuetionSatisfactionbUSer(Long user_id, Long idusersat, String namesat); 

	public ResponseEntity<?>  deleteSatisfaction(Satisfaction satisfaction);
	List<Answer_Satisfaction> Affecter_Answer_Question_Satisfaction(Long user_id, Long idsat,List<Review> answers);
	public ResponseEntity<?> getAllQuestionsbySatisfaction(Long user_id, Long idsat) ;

}

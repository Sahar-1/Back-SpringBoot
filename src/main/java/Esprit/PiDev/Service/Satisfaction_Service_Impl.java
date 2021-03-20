package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Question_Satisfaction;
import Esprit.PiDev.Entity.Satisfaction;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Question_Satisfaction_Repository;
import Esprit.PiDev.Repository.Satisfaction_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Satisfaction_Service_Impl implements Satisfaction_Service{
	@Autowired
	Satisfaction_Repository sat_rep;
	@Autowired
	User_Repository us_rep;
	@Autowired
	Question_Satisfaction_Repository quest_rep;
	 
@Override
public ResponseEntity<?> addSatisfaction(Satisfaction satisfaction, Long iduser) {
	// TODO Auto-generated method stub
	Dbo_User user=us_rep.findById(iduser).orElse(null);
	List<Question_Satisfaction> quests=(List<Question_Satisfaction>)quest_rep.findAll();

		if(sat_rep.findByUser(iduser)==null)
		{
			
		satisfaction.setQuestions(quests.subList(quests.size()-10, quests.size()));
		
		satisfaction.setUser(user);
		List<Question_Satisfaction> questss=quest_rep.findAll();

		for (Question_Satisfaction quest : satisfaction.getQuestions() ) {
						
		for (Question_Satisfaction ques : questss) {
			
			if(!ques.getQuestion_Sat().equals(quest.getQuestion_Sat()))
			{
				
				satisfaction.getQuestions().add(ques);

			}
		}

			
		}
		}
		else
			
		{

			return ResponseEntity.ok(new MessageResponse("erreur"));

			
		}
		
		
		 return ResponseEntity.ok(new MessageResponse("success" + sat_rep.save(satisfaction)));

	
	
}

@Override
public ResponseEntity<?>  deleteSatisfaction(Satisfaction satisfaction) {
		// TODO Auto-generated method stub
	if ( satisfaction != null) {
		sat_rep.delete(satisfaction);
		return ResponseEntity.ok(new MessageResponse("satisfaction est supprimé "));
	} else {
		return ResponseEntity.ok(new MessageResponse("satisfaction n'existe pas"));
	}
	}

@Override
public ResponseEntity<?>  deleteSatisfactionById(Long id) {
		// TODO Auto-generated method stub
	
	 return ResponseEntity.ok(new MessageResponse("success" + 	sat_rep.findById(id).get()));

	}
@Override
	public Satisfaction findSatisfactionById(Long id) {
		// TODO Auto-generated method stub
		return sat_rep.findById(id).get();
	}
@Override
	public List<Satisfaction> retrieveAllSatisfactions() {
		// TODO Auto-generated method stub
		return (List<Satisfaction> ) sat_rep.findAll();
	}
 @Override
	public Satisfaction retrieveSatisfaction(Satisfaction satisfaction) {
		// TODO Auto-generated method stub
		return sat_rep.findById(satisfaction.getId()).get();
	}
 @Override
 public ResponseEntity<?>  saveOrUpdate(Satisfaction satisfaction ,Long user_id,Long sat) {
		// TODO Auto-generated method stub
	 Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			Satisfaction Satisfaction_To_Update =sat_rep.findById(sat).orElse(null);
			if ( satisfaction != null) {
				
				Satisfaction_To_Update.setName(satisfaction.getName());
		
				return ResponseEntity.ok(new MessageResponse("satisfaction est bien modifiée "));
			} 
			else {
				return ResponseEntity.ok(new MessageResponse("satisfaction n'existe pas"));
			}

	}
		return ResponseEntity.ok(new MessageResponse("athhh"));

 
 }
}


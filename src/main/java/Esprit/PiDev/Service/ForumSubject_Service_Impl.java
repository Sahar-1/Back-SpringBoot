package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.ForumSubject;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Interface_Forum_Subject_Service;
import Esprit.PiDev.Repository.Forum_Subject_Repository;
import Esprit.PiDev.Repository.User_Repository;
@Service
public class ForumSubject_Service_Impl implements Interface_Forum_Subject_Service {
	
	
	@Autowired
	Forum_Subject_Repository frep;
	
	@Autowired
	User_Repository ur1;
	
	
	
	
	@Override
	public ForumSubject Add_Forum_Subjet(ForumSubject F) {
	return	frep.save(F);		
	}

	@Override
	public ForumSubject Update_ForumSubject(ForumSubject C) {
	return	frep.save(C);		
	}

	@Override
	public List<ForumSubject> Retrieve_All_Forum_Subject() {
		
		return (List<ForumSubject>)frep.findAll();
	}

	@Override
	public int getNombreForum() {
		return frep.countForumsSubject();
	}

	@Override
	public ForumSubject retrieve_Forum_Subject(Long id) {
		return frep.findById(id).get();
	}

	@Override
	public void delete_Forum_Subject(Long id) {
		frep.deleteById(id);
	}
	@Override

	public ResponseEntity<?> RatingStatus(float status,Long user_id, Long idSubject) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			
			
			
			ForumSubject sujet_To_Update = frep.findById(idSubject).orElse(null);
			dbo_User.getForumSubjects().add(sujet_To_Update);
			
		
			
			
			
			if ( sujet_To_Update != null & status <= 10) {
				float nov= (float) (sujet_To_Update.getStatus()+(status* 0.15));

				sujet_To_Update.setStatus(nov);
			
				frep.save(sujet_To_Update);
				return ResponseEntity.ok(new MessageResponse("rating bien ajouter "));
			} else {
				return ResponseEntity.ok(new MessageResponse("rating pas ajouter "));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un parent"));

		}

	}
	
}

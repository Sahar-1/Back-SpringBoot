package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.ForumSubject;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Forum_Comment_Repository;
import Esprit.PiDev.Repository.Forum_Subject_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class ForumSubject_Service_Impl  {
	
	
	@Autowired
	Forum_Subject_Repository frep;
	
	@Autowired
	Forum_Comment_Repository ComREp;
	
	@Autowired
	User_Repository ur1;
	
	
	@Autowired
	Garden_Repository gard_rep;
	
	@Autowired
	TempUserService userService;
	
	
	public void Add_ForumSubject(ForumSubject F ,/*, Long user_connect,*/ Long idgarden) {
		Dbo_User dbo_User = userService.getConnectedUser();
		Garden garden =gard_rep.findById(idgarden).orElseThrow(()-> new RuntimeException("null identify"));
		//Dbo_User dbo_User_connect = ur1.findById(user_connect).orElseThrow( ()-> new RuntimeException("null identify"));
		
	//	if (dbo_User_connect.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			F.setGarden(garden);
		//	F.setUser(dbo_User_connect);
			frep.save(F);
		//	return ResponseEntity.ok(new MessageResponse("forum subject is saved"));

	//	}

	//	else {

	//		return ResponseEntity.ok(new MessageResponse("user isn't parent"));

	//	}

	}

	

	public void updateSubject(ForumSubject F,Long subject_id/*, Long user_id*/) {
		Dbo_User dbo_User = userService.getConnectedUser();

		//Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
	//	if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			
			ForumSubject Subject_To_Update =frep.findById(subject_id).orElse(null);
			if ( Subject_To_Update != null) {
		Subject_To_Update.setTitle(F.getTitle());
		Subject_To_Update.setQuestion(F.getQuestion());
		Subject_To_Update.setPostedDate(F.getPostedDate());
		Subject_To_Update.setStatus(F.getStatus());
		Subject_To_Update.setVoteCount(F.getVoteCount());
		
		
		/*if(Subject_To_Update.getUser().getId() == user_id){*/
			frep.save(Subject_To_Update);
			
	
			/*
		}else{
			return ResponseEntity.badRequest().body("user can only change his subjects");
		}
		
		return ResponseEntity.ok(new MessageResponse("Subject Forum is modified "));
		}
		else {
			return ResponseEntity.ok(new MessageResponse("Subject Forum isn't modified"));
		}
	}

	else {

		return ResponseEntity.ok(new MessageResponse("user isn't parent"));

	}*/

			}}	
		
	
	
	
	
	
	public List<ForumSubject> Retrieve_All_Forum_Subject() {
		Dbo_User dbo_User = userService.getConnectedUser();
		/*Dbo_User dbo_User = ur1.findById(user_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT)))

		{
		*/
		
		List<ForumSubject> list = (List<ForumSubject>) frep.findAll();
		return list;
			

		//}
	//	{
	//		return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

	//	}
	}


	public int getNombreForum() {
		Dbo_User dbo_User = userService.getConnectedUser();
		return frep.countForumsSubject();
	}



	

	
	

	public ResponseEntity<?> retrieve_Forum_Subject(long user_id, Long id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT)))

		{
			return ResponseEntity.ok(new MessageResponse("" + frep.findById(id).get()));

		}
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

		}
	}
	

	public ForumSubject  findSubject (Long id){
	Dbo_User dbo_User = userService.getConnectedUser();
		return frep.findById(id).get();
	
	}



	public void deleteSubject(Long id) {
	Dbo_User dbo_User = userService.getConnectedUser();
	frep.deleteById(id);
	}
	
	
	
/*	
	public ResponseEntity<?> delete_Forum_Subject(long user_id, Long idForum) {
		
		
		ForumSubject forumSujet = frep.findById(idForum).orElse(null);
		if(forumSujet == null)
		{
			return ResponseEntity.ok("forum sujet n'existe pas");	

		}
		else
		{
			if(forumSujet.getUser().getId() == user_id){
			frep.deleteById(forumSujet.getId());}
			else
			{
				return ResponseEntity.badRequest().body("user can only change his subjects");
			}
			
			return ResponseEntity.ok(new MessageResponse("forum sujet est supprimé"));	
		}
		
	
		
		
	}

*/
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

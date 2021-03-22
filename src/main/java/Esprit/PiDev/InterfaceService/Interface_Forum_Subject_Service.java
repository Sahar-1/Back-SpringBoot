package Esprit.PiDev.InterfaceService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.ForumSubject;

public interface Interface_Forum_Subject_Service {

	ForumSubject Add_Forum_Subjet(ForumSubject F);
	ForumSubject Update_ForumSubject(ForumSubject C);
	List<ForumSubject> Retrieve_All_Forum_Subject();
	int getNombreForum();
	ForumSubject retrieve_Forum_Subject(Long id);
	void delete_Forum_Subject(Long id);
	public ResponseEntity<?> RatingStatus(float status,Long user_id, Long idSubject) ;

	
}

package Esprit.PiDev.InterfaceService;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.ForumSubject;

public interface Interface_Forum_Subject_Service {

	ResponseEntity<?> Add_ForumSubject(ForumSubject F, Long iduser, Long idgarden);
	ResponseEntity<?> updateSubject(ForumSubject F,Long subject_id, Long user_id, Long idgarden);
	public ResponseEntity<?> Retrieve_All_Forum_Subject(long user_id);
	//public int getNombreForum();
	public ResponseEntity<?> retrieve_Forum_Subject(Long id);
	void delete_Forum_Subject(Long id);
	ResponseEntity<?> RatingStatus(float status,Long user_id, Long idSubject) ;

	
}

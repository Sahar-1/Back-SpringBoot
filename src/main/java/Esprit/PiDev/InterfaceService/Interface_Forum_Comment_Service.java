package Esprit.PiDev.InterfaceService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.ForumComment;

public interface Interface_Forum_Comment_Service {
	
	
	
	 int getNombreForum() ;
	 ForumComment retrieveForum(Long id);
	 void deleteForum(Long id);
	 List<ForumComment> Retrieve_All_Forum() ;
	 ForumComment Update_Forum(ForumComment F);
	 ResponseEntity<?> Add_Forum(ForumComment F,Long idsubject,Long iduser);
	 public ResponseEntity<?> StatistiqueCommentSubjectbyUser(Long user_id, Long iduserforum, String question) ;

}

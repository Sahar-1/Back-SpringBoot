package Esprit.PiDev.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Comment;
import Esprit.PiDev.Service.Comment_Service;
import Esprit.PiDev.Service.Session_UserDetails;

@RestController
public class Comment_Controller_Rest {
	@Autowired
	Comment_Service commentservice;
	
	// ****************************affichercomment************************************//

	   @GetMapping("/retrieve-all-comments")
	   public List<Comment> getComments() {
	          List<Comment> list = commentservice.retrieveAllComments();
	          return list;
	   } 
		// ****************************affichercomment************************************//
	   
	   

		// ****************************affichercommentbyid************************************//

	   @GetMapping("/retrieve-comment/{comment-id}")	   
		   public ResponseEntity<?> retrieveComment(Authentication auth,@PathVariable("comment-id") Long commentid) {
			   SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		     return  commentservice.findCommentById(commentid,userDetails.getId());
	   } 
		// ****************************affichercommentbyid************************************//
	   

	   
		// ****************************addComment************************************//

	   @RequestMapping("/add_comment/{id_post}")
		   public ResponseEntity<?> addComment(Authentication auth,@RequestBody Comment comment,@PathVariable("id_post") Long id_post) {
			   SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			  return commentservice.addComment(comment,id_post,userDetails.getId()); 
	   }
		// ****************************addComment************************************//
	   
	   

		// ****************************removecomment************************************//

	   @DeleteMapping("/remove-comment/{comment-id}") 
	   public ResponseEntity<?> removeComment(Authentication auth,@PathVariable("comment-id") Long commentid) { 
		   SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		  return commentservice.deleteById(commentid,userDetails.getId());
	   }
		// ****************************removecomment************************************//
	   
	   
		// ****************************modifycomment************************************//

	   @PutMapping("/modify-comment/{comment-id}") 
	   public ResponseEntity<?> modifyComment(Authentication auth,@RequestBody Comment comment,@PathVariable("comment-id") Long commentid) {
		   SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		 return  commentservice.saveOrUpdate(comment,userDetails.getId(),commentid);
	   }
		// ****************************modifycomment************************************//

	
	
	
}

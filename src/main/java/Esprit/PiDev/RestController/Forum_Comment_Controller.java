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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.ForumComment;
import Esprit.PiDev.Repository.Forum_Comment_Repository;
import Esprit.PiDev.Service.ForumComment_Service_Impl;
import Esprit.PiDev.Service.Session_UserDetails;

@RestController
public class Forum_Comment_Controller {

	@Autowired
	ForumComment_Service_Impl fs;
	
	
	@Autowired
	Forum_Comment_Repository forumRep;
	
	
	@RequestMapping("/addForumComment/{subject_id}/{user_id}")
	@ResponseBody
   public ResponseEntity<?> ajouterForumComment(@RequestBody ForumComment f, @PathVariable("subject_id") Long subjectid , @PathVariable("user_id") Long userid) {
	return fs.Add_Forum(f,subjectid,userid);

	}
	
	
	@GetMapping("/getNombreForumComment")
	  @ResponseBody
	  public int getNombreForumComment() {
	    int nb=	 fs.getNombreForum();
	    return nb;
	  }
	
	
	@GetMapping("/getAllForumsComment")
	  @ResponseBody
	  public List<ForumComment> getAllForums() {
		 return fs.Retrieve_All_Forum();
	  }
	
	
	@DeleteMapping("/removeForumComment/{comment-id}")
	public void removeForum(@PathVariable("comment-id") Long commentId){
		fs.deleteForum(commentId);
	}
	
	@PutMapping("update-forumComment")
	public void updateForum(@RequestBody ForumComment f){
		fs.Update_Forum(f);
		
	}
	
	@GetMapping("/retrieve-forumComment/{comment-id}")
	public ForumComment retrieveForum(@PathVariable("comment-id") Long commentId) {
	return fs.retrieveForum(commentId);
	}
	
	@GetMapping("/chercher_badWords/{user_id}")
	public ResponseEntity<?> chercher_badWords( @PathVariable("user_id") Long userid) {
	return fs.chercher_badWords(userid);
	}

	@GetMapping("/statisques/{iduserforum}/{question}")
	public ResponseEntity<?>  Statistique(Authentication auth,@PathVariable("iduserforum") Long iduserforum,@PathVariable("question")String question){
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		
		return fs.StatistiqueCommentSubjectbyUser(userDetails.getId(), iduserforum, question);
		
		
		
	}
	
	
	
}

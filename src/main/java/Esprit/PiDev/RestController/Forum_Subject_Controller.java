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

import Esprit.PiDev.Entity.ForumSubject;
import Esprit.PiDev.Repository.Forum_Subject_Repository;
import Esprit.PiDev.Service.ForumSubject_Service_Impl;
import Esprit.PiDev.Service.Session_UserDetails;


@RestController
public class Forum_Subject_Controller {
	
	
	@Autowired
	ForumSubject_Service_Impl fs;
	
	
	@Autowired
	Forum_Subject_Repository forumRep;
	
	
	
	
	@RequestMapping("/addForumSubject")
	@ResponseBody
   public ForumSubject ajouterForumSubject(@RequestBody ForumSubject f) {
	return fs.Add_Forum_Subjet(f);

	}
	
	
	@GetMapping("/getNombreForumSubject")
	  @ResponseBody
	  public int getNombreForumSubject() {
	    int nb=	 fs.getNombreForum();
	    return nb;
	  }
	
	
	@GetMapping("/getAllForumsSubject")
	  @ResponseBody
	  public List<ForumSubject> getAllForums() {
		 return fs.Retrieve_All_Forum_Subject();
	  }
	
	
	@DeleteMapping("/removeForum/{forum-id}")
	public void removeForum(@PathVariable("forum-id") Long forumId){
		fs.delete_Forum_Subject(forumId);
	}
	
	@PutMapping("/update-forum")
	public void updateForum(@RequestBody ForumSubject f){
		fs.Update_ForumSubject(f);
		
	}
	
	@GetMapping("/retrieve-forum/{forum-id}")
	public ForumSubject retrieveForum(@PathVariable("forum-id") Long forumId) {
	return fs.retrieve_Forum_Subject(forumId);
	}
	
	@PutMapping("/RatingStatus/{status}/{id-subject}")
	public ResponseEntity<?> RatingStatus( Authentication auth,@PathVariable("status") float status,@PathVariable("id-subject") Long idSubject) {
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	
		return fs.RatingStatus(status, userDetails.getId(),idSubject);
		
	}
	

	
  }
	
	
	

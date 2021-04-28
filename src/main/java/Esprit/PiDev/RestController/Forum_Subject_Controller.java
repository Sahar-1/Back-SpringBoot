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
	
	
	@RequestMapping("/addForumSubject/{garden-id}")
	@ResponseBody
	public void ajouterForumSubject(Authentication authentication, @RequestBody ForumSubject F,  @PathVariable("garden-id") Long gardenid) {
		 
		
	//	if ( !(authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_PARENT")))) {
	    //    throw new API_Request_Exception_UNAUTHORIZED_STATUS_403(authentication.getName().toUpperCase() + " IS UNAUTHORIZED CONTENT WITH THIS AUTHORITY !!! ");
	 //   } else {
	  //  	SecurityContextHolder.getContext().setAuthentication(authentication);
		//	Session_UserDetails userDetails = (Session_UserDetails) authentication.getPrincipal();
			 fs.Add_ForumSubject(F,gardenid); 
	   // }
	
	}
	
	
	
	
	
	
	@GetMapping("/getNombreForumSubject")
	  @ResponseBody
	  public int getNombreForumSubject() {
	    int nb=	 fs.getNombreForum();
	    return nb;
	  }
	
	
	
	
	@GetMapping("/getAllForumsSubject")
	public List<ForumSubject> getAllForumSubject(Authentication auth)
  {
	
			//SecurityContextHolder.getContext().setAuthentication(auth);
			//Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return fs.Retrieve_All_Forum_Subject();

		
  }
	
	@GetMapping("/retrieve-Subject/{subject-id}")
	@ResponseBody
	public ForumSubject retrieveSubject(@PathVariable("subject-id") Long subjectId)
  {
	
			//SecurityContextHolder.getContext().setAuthentication(auth);
		//	Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return fs.findSubject(subjectId);

		
  }
	
	
	@GetMapping("/retrieve-forumSubject/{forum-id}")
	public ResponseEntity<?> retrieveForumSubject(Authentication auth,@PathVariable("forum-id") Long forumId)
  {
	
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return fs.retrieve_Forum_Subject(userDetails.getId(),forumId);

		
  }
	
	@PutMapping("/update-forumSubject/{forum-id}")
	public void updateForumSubject(Authentication authentication,@RequestBody ForumSubject f,@PathVariable("forum-id") Long forumId){
	
	//if ( !(authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_PARENT")))) {
       // throw new API_Request_Exception_UNAUTHORIZED_STATUS_403(authentication.getName().toUpperCase() + " IS UNAUTHORIZED CONTENT WITH THIS AUTHORITY !!! ");
   // } else {
   // 	SecurityContextHolder.getContext().setAuthentication(authentication);
	//	Session_UserDetails userDetails = (Session_UserDetails) authentication.getPrincipal();
		  fs.updateSubject(f,forumId); 
		//return  fs.updateSubject(authentication,f,forumId,userDetails.getId()); 
  //  }
	}
	
	
	
	
	
	/*
	
	@DeleteMapping("/removeForumSubject/{forum-id}")
	public void removeForumSubject(Authentication auth,@PathVariable("forum-id") Long forumId) throws ParseException {
		//SecurityContextHolder.getContext().setAuthentication(auth);
		//Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		fs.deleteSubject(forumId);
	
	}*/
	
	@DeleteMapping("/removeForumSubject/{sujet-id}") 
	   public void removeSubject(@PathVariable("sujet-id") Long id) { 
	     fs.deleteSubject(id);
	   } 
	
	@PutMapping("/RatingStatus/{status}/{id-subject}")
	public ResponseEntity<?> RatingStatus( Authentication auth,@PathVariable("status") float status,@PathVariable("id-subject") Long idSubject) {
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	
		return fs.RatingStatus(status, userDetails.getId(),idSubject);
		
	}
	/*
	@PutMapping(value = "/affecterCommentASubject/{idcom}/{idsub}")
	public void affecterCommentASubject(@PathVariable("idcom") int commentId, @PathVariable("idsub") int subjectId) {
		fs.affecterCommentASubject(commentId, subjectId);
		

	}
*/
	
  }
	
	
	

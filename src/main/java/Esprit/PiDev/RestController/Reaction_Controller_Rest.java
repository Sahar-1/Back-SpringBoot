package Esprit.PiDev.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Comment;
import Esprit.PiDev.Entity.Reaction;
import Esprit.PiDev.Service.Reaction_Service;
import Esprit.PiDev.Service.Session_UserDetails;
@RestController
public class Reaction_Controller_Rest {
	@Autowired
	Reaction_Service reactionservice;
	
	   @GetMapping("/retrieve-all-reactions")
	   public List<Reaction> getReactions() {
	          List<Reaction> list = reactionservice.retrieveAllReactions();
	          return list;
	   } 
	
	  /* @GetMapping("/retrieve-reaction/{reaction-id}")
	   
	public ResponseEntity<?> retrieveReaction(Authentication auth,@PathVariable("reaction-id") Long reactionid) {
			   SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		     return  reactionservice.findReactionById(reactionid,userDetails.getId());
	   } */
	   
	   
	   @RequestMapping("/add-reaction/{id_post}")	   
		   public ResponseEntity<?> addReaction(Authentication auth,@RequestBody Reaction reaction,@PathVariable("id_post") Long id_post) {
			   SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			  return reactionservice.addReaction(reaction,id_post,userDetails.getId()); 
	   }

	   @DeleteMapping("/remove-reaction/{reaction-id}") 
	   public ResponseEntity<?> removeReaction(Authentication auth,@PathVariable("reaction-id") Long reactionid) { 
		   SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		  return reactionservice.deleteById(reactionid,userDetails.getId());
	   }

	   @PutMapping("/modify-reaction/{reaction-id}") 
	  
	   public ResponseEntity<?> modifyReaction(Authentication auth,@RequestBody Reaction reaction,@PathVariable("reaction-id") Long reactionid) {
		   SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		 return  reactionservice.saveOrUpdate(reaction,userDetails.getId(),reactionid);
	   }
	
	
	
	
	
}

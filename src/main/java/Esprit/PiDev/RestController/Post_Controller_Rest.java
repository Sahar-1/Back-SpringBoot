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

import Esprit.PiDev.Entity.Comment;
import Esprit.PiDev.Entity.Post;
import Esprit.PiDev.Service.Post_Service;
import Esprit.PiDev.Service.Post_Service_Impl;
import Esprit.PiDev.Service.Session_UserDetails;

@RestController
public class Post_Controller_Rest {

	@Autowired
	Post_Service postservice;
	
	
	// ****************************afficherPOST************************************//

	   @GetMapping("/retrieve-all-posts")
	   public List<Post> getPosts() {
	          List<Post> list = postservice.retrieveAllPost();
	          return list;
	   } 
		// ****************************afficherpost************************************//
	   

		// ****************************afficherpostbyid************************************//

	  /* @GetMapping("/retrieve-post/{post-id}")
	   public ResponseEntity<?> retrievePost(Authentication auth,@PathVariable("post-id") Long postid) {
		   SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	     return  postservice.findPostById(postid,userDetails.getId());
	   } */
	   @GetMapping("/retrieve-post/{post-id}")
	   public void retrievePost(@PathVariable("post-id") Long postid) {
	       postservice.findPostById(postid);
	   } 
		// ****************************afficherpostbyid************************************//

	   
	   
	   
		// ****************************addpost************************************//
		//http://localhost:8080/add-post	
	   @RequestMapping("/add-post")
	   public ResponseEntity<?>  addPost(Authentication auth,@RequestBody Post post) {
		   SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		  return postservice.addPost(post,userDetails.getId()); 
		  
		  
	   }
		// ****************************addpost************************************//

	   
		// ****************************deletepost************************************//

	   @DeleteMapping("/remove-post/{post-id}") 
	   public ResponseEntity<?> removePost(Authentication auth,@PathVariable("post-id") Long postid) { 
		   SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		  return postservice.deleteById(postid,userDetails.getId());
	   } 

		// ****************************deletepost************************************//

	   
		// ****************************modifypost************************************//

	   @PutMapping("/modify-post/{post-id}") 
	   public ResponseEntity<?> modifyPost(Authentication auth,@RequestBody Post post,@PathVariable("post-id") Long postid) {
		   SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		 return  postservice.saveOrUpdate(post,userDetails.getId(),postid);
		 
	   }
		// ****************************modifypost************************************//

		// ****************************sharepost************************************//
	
	   @PutMapping("/share-post/{post-id}") 
	   public ResponseEntity<?> share(Authentication auth,@PathVariable("post-id") Long postid) {
		   SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		 return  postservice.sharePost(postid,userDetails.getId());
		 
	   }
		// ****************************sharepost************************************//
	   
	   
	   
		// ****************************SignalerPost************************************//
	   @PutMapping("/SignalerPost/{post-id}") 
	   public ResponseEntity<?> signaler(Authentication auth,@PathVariable("post-id") Long postid) {
		   SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		 return  postservice.SignalerPost(postid,userDetails.getId());
		 
	   }
		// ****************************SignalerPost************************************//

	// ****************************Chercherpost************************************//
			@GetMapping("/chercher-post/{search}")
			public ResponseEntity<?> ChercherPost(Authentication auth, @PathVariable String search) {

				SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
				return postservice.ChercherPost(userDetails.getId(), search);
			}
			// ****************************Chercherpost************************************//
	
			
			// ****************************StatistiquePost************************************//

			@GetMapping("/Statistique-Post")
			public ResponseEntity<?> StatistiquePost(Authentication auth)
			   {
				SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
				return postservice.StatistiquePosts(userDetails.getId());
				}
			// ****************************StatistiquePost************************************//

			
			
}

package Esprit.PiDev.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import Esprit.PiDev.Entity.ForumSubject;
import Esprit.PiDev.Repository.Forum_Comment_Repository;
import Esprit.PiDev.Service.ForumComment_Service_Impl;
import Esprit.PiDev.Service.Session_UserDetails;

@RestController
public class Forum_Comment_Controller {

	@Autowired
	ForumComment_Service_Impl forumService;

	@Autowired
	Forum_Comment_Repository forumRep;

	// @RequestMapping("/addForumCommentToSubject/{subject_id}/{user_id}")
	@RequestMapping("/addForumCommentToSubject/{subject_id}")
	@ResponseBody
	public void affect_comment_to_subject(@RequestBody ForumComment comment, @PathVariable("subject_id") Long subjectId) {
		forumService.addForumCommentToSubject(comment, subjectId);

	}

	@GetMapping("/getNombreForumComment")
	@ResponseBody
	public int getNombreForumComment() {
		int nb = forumService.getNombreForum();
		return nb;
	}

	@GetMapping("/getAllForumsComment")
	public void getAllForumSubject(Authentication auth) {

		// SecurityContextHolder.getContext().setAuthentication(auth);
		// Session_UserDetails userDetails = (Session_UserDetails)
		// auth.getPrincipal();
		forumService.Retrieve_All_Forum_Comment(/* userDetails.getId() */);

	}

	@DeleteMapping("/removeForumComment/{comment-id}")
	public void removeForum(@PathVariable("comment-id") Long commentId) {
		forumService.deleteForum(commentId);
	}

	@PutMapping("/update-forumComment")
	public void modifyPost(Authentication auth, @RequestBody ForumComment forumComment) {
		// SecurityContextHolder.getContext().setAuthentication(auth);
		// Session_UserDetails userDetails = (Session_UserDetails)
		// auth.getPrincipal();
		forumService.updateForum(forumComment);

	}

	@GetMapping("/retrieve-forumComment/{comment-id}")
	public void retrieveForumSubject(Authentication auth, @PathVariable("comment-id") Long commentId) {

		// SecurityContextHolder.getContext().setAuthentication(auth);
		// Session_UserDetails userDetails = (Session_UserDetails)
		// auth.getPrincipal();
		// return
		forumService.retrieveForumComment(/* userDetails.getId(), */commentId);

	}

	@GetMapping("/findCommentBySubject/{subject}")
	public ResponseEntity findCommentBySubject(Authentication auth, @PathVariable("subject") ForumSubject subject) {

		// SecurityContextHolder.getContext().setAuthentication(auth);
		// Session_UserDetails userDetails = (Session_UserDetails)
		// auth.getPrincipal();
		// return
		try {
			return ResponseEntity.ok(forumService.findCommentBySubject(subject));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@GetMapping("/statisques/{iduserforum}/{question}")
	public ResponseEntity<?> Statistique(Authentication auth, @PathVariable("iduserforum") Long iduserforum,
			@PathVariable("question") String question) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();

		return forumService.StatistiqueCommentSubjectbyUser(userDetails.getId(), iduserforum, question);

	}

	/*
	 * @GetMapping("/chercher_badWords/{user_id}") public ResponseEntity<?>
	 * chercher_badWords( @PathVariable("user_id") Long userid) { return
	 * fs.chercher_badWords(userid); }
	 */
	/*
	 * @GetMapping("/statisquesSujet/{idforum}") public ResponseEntity<?>
	 * Statistique(Authentication auth,@PathVariable("iduserforum") Long
	 * idforum){
	 * 
	 * SecurityContextHolder.getContext().setAuthentication(auth);
	 * Session_UserDetails userDetails = (Session_UserDetails)
	 * auth.getPrincipal();
	 * 
	 * return fs.StatistiqueMaxCommentSubject(userDetails.getId(), idforum);
	 * 
	 * 
	 * 
	 * }
	 */
}

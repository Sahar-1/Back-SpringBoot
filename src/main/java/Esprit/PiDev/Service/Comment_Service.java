package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Comment;

public interface Comment_Service {
	
	
	
	public ResponseEntity<?>  addComment(Comment comment,Long idpost, Long iduser);
	public ResponseEntity<?>  deleteById(Long id,Long user_id);

	List<Comment> retrieveAllComments();

	 public ResponseEntity<?>  saveOrUpdate(Comment comment ,Long user_id,Long commentid);

	public ResponseEntity<?> findCommentById(Long id,Long user_id);
	
	public Comment retrieveComment(Comment comment);

	
}

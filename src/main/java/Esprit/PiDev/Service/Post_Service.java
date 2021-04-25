package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import Esprit.PiDev.Entity.Post;

public interface Post_Service {
	public ResponseEntity<?> addPost(Post post, Long iduser);

	List<Post> retrieveAllPost();

	//public ResponseEntity<?> findPostById(Long id, Long user_id);
	public ResponseEntity<?>  deleteById(Long id,Long user_id);
	 public ResponseEntity<?>  saveOrUpdate(Post post ,Long user_id,Long postid);
	//public ResponseEntity<?>  saveOrUpdate(Post post ,Long user_id);
	Post retrievePost(Post post);
	//Post findPostById(Long id);
     Post findPostById(Long id);
     public ResponseEntity<?>  sharePost(Long idpost, Long user_id);
     public ResponseEntity<?> SignalerPost(Long idpost, Long user_id);
     public ResponseEntity<?>ChercherPost(long user_id,String search);
     public ResponseEntity<?> StatistiquePosts(Long user_id);
}

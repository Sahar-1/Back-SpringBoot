package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Comment;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Post;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Comment_Repository;
import Esprit.PiDev.Repository.Post_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Comment_Service_impl implements  Comment_Service{
	
	
	@Autowired
	Comment_Repository com_rep;
	@Autowired
	User_Repository usrep;
	@Autowired
	Post_Repository post_rep;
	

@Override
public ResponseEntity<?>  addComment(Comment comment,Long idcom, Long iduser) {
	Post post=post_rep.findById(idcom).orElse(null);
	 Dbo_User dbo_User = usrep.findById(iduser).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			if ( post != null) {
				comment.setUser(dbo_User);
	if ( comment != null) {
	 return ResponseEntity.ok(new MessageResponse("success" + 	 com_rep.save(comment)));
		} 
		else {
			return ResponseEntity.ok(new MessageResponse("Le commentaire est vide"));
		}

}
			}
	return ResponseEntity.ok(new MessageResponse("n'est pas PARENT"));


}


@Override
public ResponseEntity<?>  deleteById(Long id,Long user_id) {
	// TODO Auto-generated method stub
	 Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			if ( id != null) {
				
				com_rep.deleteById(id);
	 return ResponseEntity.ok(new MessageResponse("success" ));
	 
	 
			} 
			else {
				return ResponseEntity.ok(new MessageResponse("id null"));
			}

	}
		return ResponseEntity.ok(new MessageResponse("n'est pas PARENT"));

}

@Override
public ResponseEntity<?> findCommentById(Long id,Long user_id) {
	 Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			if ( id != null) {
	
				 return ResponseEntity.ok(new MessageResponse("success" + 	 com_rep.findById(id).get()));

			} 
			else {
				return ResponseEntity.ok(new MessageResponse("id null"));
			}

	}
		return ResponseEntity.ok(new MessageResponse("n'est pas PARENT"));

}

@Override
	public List<Comment> retrieveAllComments() {
		// TODO Auto-generated method stub
		return (List<Comment>)com_rep.findAll();
	}
@Override
	public Comment retrieveComment(Comment comment) {
		// TODO Auto-generated method stub
		return com_rep.findById(comment.getId()).get();
	}
 @Override
 public ResponseEntity<?>  saveOrUpdate(Comment comment ,Long user_id,Long commentid) {
		// TODO Auto-generated method stub
	 Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			Comment comment_To_Update =com_rep.findById(commentid).orElse(null);
			if ( comment_To_Update != null) {
				
				comment_To_Update.setText(comment.getText());
				com_rep.save(comment_To_Update);


				return ResponseEntity.ok(new MessageResponse("Votre commentaire est bien modifié "));
			} 
			else {
				return ResponseEntity.ok(new MessageResponse("Le commentaire n'existe pas"));
			}

	}
		return ResponseEntity.ok(new MessageResponse("L'utilisateur n'est pas PARENT"));

 
 }
 
}

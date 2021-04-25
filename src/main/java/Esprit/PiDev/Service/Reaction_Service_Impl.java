package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Post;
import Esprit.PiDev.Entity.Reaction;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Post_Repository;
import Esprit.PiDev.Repository.Reaction_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Reaction_Service_Impl implements Reaction_Service {

	@Autowired
	Reaction_Repository rea_rep;
	@Autowired
	User_Repository usrep;
	@Autowired
	Post_Repository post_rep;

	@Override
	public ResponseEntity<?> addReaction(Reaction rea, Long idpost, Long iduser) {
		Post post = post_rep.findById(idpost).orElse(null);
		Dbo_User dbo_User = usrep.findById(iduser).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			//System.out.println("hhhhhhhhhhhhhhhh rani post"+post);
			
			if (post != null) {
				
				rea.setUser(dbo_User);
		         rea.setPost(post);
				post.getReactions().add(rea);
				post_rep.save(post);
				 rea_rep.save(rea);
				 
				 
				return ResponseEntity.ok(new MessageResponse("success" ));

			   } else {
				return ResponseEntity.ok(new MessageResponse("La post null"));

			}
		} else {
			return ResponseEntity.ok(new MessageResponse("n'est pas PARENT"));

		}

	}

	@Override
	public ResponseEntity<?> deleteById(Long id, Long user_id) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			if (id != null) {
				rea_rep.deleteById(id);
				return ResponseEntity.ok(new MessageResponse("success"));
			} else {
				return ResponseEntity.ok(new MessageResponse("id null"));
			}

		}
		return ResponseEntity.ok(new MessageResponse("n'est pas PARENT"));

	}

	/*
	 * @Override public ResponseEntity<?> findReactionById(Long id,Long user_id)
	 * { Dbo_User dbo_User = usrep.findById(user_id).orElse(null); if
	 * (dbo_User.getRole().stream().anyMatch(e ->
	 * e.getName().equals(ERole.PARENT))) { if ( id != null) { return
	 * ResponseEntity.ok(new MessageResponse("success" +
	 * rea_rep.findById(id).get()));
	 * 
	 * } else { return ResponseEntity.ok(new MessageResponse("id null")); }
	 * 
	 * } return ResponseEntity.ok(new MessageResponse("n'est pas PARENT"));
	 * 
	 * }
	 */

	@Override
	public List<Reaction> retrieveAllReactions() {
		// TODO Auto-generated method stub
		return (List<Reaction>) rea_rep.findAll();
	}

	/*
	 * @Override public Reaction retrieveReaction(Reaction rea) { // TODO
	 * Auto-generated method stub return rea_rep.findById(rea.getId()).get(); }
	 */
	@Override
	public ResponseEntity<?> saveOrUpdate(Reaction rea, Long user_id, Long reactionid) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			Reaction rea_To_Update = rea_rep.findById(reactionid).orElse(null);
			if (rea_To_Update != null) {

				rea_To_Update.setLiked(rea.isLiked());
				rea_rep.save(rea_To_Update);

				if (rea.isLiked()==true) {
					return ResponseEntity.ok(new MessageResponse("Vous venez de liker le post "));

				} else {
					return ResponseEntity.ok(new MessageResponse("Vous venez de disliker le post"));
				}
			} 
		}
		return ResponseEntity.ok(new MessageResponse("L'utilisateur n'est pas PARENT"));

	}
}

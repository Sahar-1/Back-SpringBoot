package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Comment;
import Esprit.PiDev.Entity.Reaction;

public interface Reaction_Service {
	
	
	public ResponseEntity<?>  addReaction(Reaction rea,Long idpost,Long iduser );
	//public ResponseEntity<?>  addReaction(Reaction rea,Long iduser);
	public List<Reaction> retrieveAllReactions();

	 public ResponseEntity<?>  saveOrUpdate(Reaction rea ,Long user_id,Long reactionid);

	 //public ResponseEntity<?> findReactionById(Long id,Long user_id);
	 //public Reaction retrieveReaction(Reaction rea);
	public ResponseEntity<?>  deleteById(Long id,Long user_id);
	}

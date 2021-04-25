package Esprit.PiDev.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Post;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Comment_Repository;
import Esprit.PiDev.Repository.Post_Repository;
import Esprit.PiDev.Repository.Reaction_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Post_Service_Impl implements Post_Service {

	@Autowired
	Post_Repository post_rep;
	@Autowired
	User_Repository usrep;
	@Autowired
	Reaction_Repository rea_rep;
	@Autowired
	Comment_Repository com_rep;

	//****************************************AddPost******************************************************//

	@Override
	public ResponseEntity<?> addPost(Post post, Long iduser) {
		Dbo_User dbo_User = usrep.findById(iduser).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			
			    dbo_User.getPosts().add(post);
				int i = 0;
				post.setSignaler(i);
			    post_rep.save(post);
				return ResponseEntity.ok(new MessageResponse("Post ajouté avec succés" ));

			} else{
		return ResponseEntity.ok(new MessageResponse("Vous n'êtes pas PARENT"));
			}

		}
	//****************************************AddPost******************************************************//

	
	//****************************************DeletePost******************************************************//

	@Override
	public ResponseEntity<?> deleteById(Long id, Long user_id) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			if (id != null) {

				post_rep.deleteById(id);
				// com_rep.deleteById(com);
				// rea_rep.deleteById(id);
				return ResponseEntity.ok(new MessageResponse("Post supprimé avec succés"));

			} else {
				return ResponseEntity.ok(new MessageResponse("Post n'existe pas "));
			}

		}else{
		return ResponseEntity.ok(new MessageResponse("Vous n'êtes pas PARENT"));
		}

	}
	//****************************************DeletePost******************************************************//

	/*
	 * public ResponseEntity<?> findPostById(Long id,Long user_id) { Dbo_User
	 * dbo_User = usrep.findById(user_id).orElse(null); if
	 * (dbo_User.getRole().stream().anyMatch(e ->
	 * e.getName().equals(ERole.PARENT))) { if ( id != null) {
	 * 
	 * return ResponseEntity.ok(new MessageResponse("success" +
	 * post_rep.findById(id).get()));
	 * 
	 * } else { return ResponseEntity.ok(new MessageResponse("id null")); }
	 * 
	 * } return ResponseEntity.ok(new
	 * MessageResponse("L'utilisateur n'est pas PARENT"));
	 * 
	 * }
	 */

	@Override
	public List<Post> retrieveAllPost() {
		// TODO Auto-generated method stub
		return (List<Post>) post_rep.findAll();
	}

	@Override
	public Post retrievePost(Post post) {
		// TODO Auto-generated method stub
		return post_rep.findById(post.getId()).get();
	}
	//****************************************FindPostById******************************************************//
	@Override
	public Post findPostById(Long id) {
		// TODO Auto-generated method stub
		return post_rep.findById(id).get();
	}
	//****************************************FindPostById******************************************************//

	
	//****************************************UpdatePost******************************************************//

	@Override
	public ResponseEntity<?> saveOrUpdate(Post post, Long user_id, Long postid) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			Post Post_To_Update = post_rep.findById(postid).orElse(null);
			if (Post_To_Update != null) {

				Post_To_Update.setTitle(post.getTitle());
				Post_To_Update.setSubject(post.getSubject());
				post_rep.save(Post_To_Update);

				return ResponseEntity.ok(new MessageResponse("Votre post est bien modifiée "));
			} else {
				return ResponseEntity.ok(new MessageResponse("Le post n'existe pas"));
			}

		} else {
		return ResponseEntity.ok(new MessageResponse("Vous n'êtes pas parent"));
		}

	}
	//****************************************UpdatePost******************************************************//

	
	//****************************************SharePost******************************************************//

	@Override
	public ResponseEntity<?> sharePost(Long idpost, Long user_id) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = usrep.findById(user_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			Post post = post_rep.findById(idpost).orElse(null);
			if (post != null) {

				dbo_User.getPosts().add(post);
				//post.getUser().add(dbo_User);
				// post.setUser(dbo_User);

				post_rep.save(post);
				return ResponseEntity.ok(new MessageResponse("Le post a été partagé avec succés"));

			} else {
				return ResponseEntity.ok(new MessageResponse("post n'existe pas"));

			}

		} else{

		return ResponseEntity.ok(new MessageResponse("L'utilisateur n'est pas parent"));
		}
	}
	//****************************************SharePost******************************************************//

	
	
	//****************************************SignalerPost******************************************************//
	@Override
	public ResponseEntity<?> SignalerPost(Long idpost, Long user_id) {

		// TODO Auto-generated method stub
		Dbo_User dbo_User = usrep.findById(user_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			Post thispost = post_rep.findById(idpost).orElse(null);
			//Dbo_User User_owner = thispost.getUser().get(0);

			if (thispost != null) {/// nullpost

				if (thispost.getSignaler() < 2) {

					
						thispost.setSignaler(thispost.getSignaler() + 1);
						post_rep.save(thispost); // nbSignaler
					return ResponseEntity.ok(new MessageResponse("Vous venez de signalez le post"));
					
					
				} else {
					/*User_owner.setNbpost_supprime(User_owner.getNbpost_supprime() + 1);
					if (User_owner.getNbpost_supprime() == 2) // ki 2 posts
					{ 
						usrep.delete(User_owner);
				return ResponseEntity.ok(new MessageResponse(" user supprimé !!!!"));

					}	*/
					post_rep.delete(thispost); // ki twali 3 tetfasakh
					return ResponseEntity.ok(new MessageResponse(" post supprimé !!!!"));

				}

			} /// nullpost

			else {
				return ResponseEntity.ok(new MessageResponse("post n'existe pas"));

			}

		}
		else{
		return ResponseEntity.ok(new MessageResponse("Vous n'êtes pas parent"));
		}
	}

	//****************************************SignalerPost******************************************************//
	
	
	//****************************************ChercherPost******************************************************//

	
	public ResponseEntity<?>ChercherPost(long user_id,String search) {
		Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			List<Post> post_search =post_rep.ChercherPost(search);
	
				return ResponseEntity.ok(new MessageResponse(""+post_search));

			}
		

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));

		}

	}
	//****************************************ChercherPost******************************************************//
	
	
	//****************************************StatPost******************************************************//

	@Override
	public ResponseEntity<?> StatistiquePosts(Long user_id) 
	{
		// TODO Auto-generated method stub
		Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
      List<Post> allposts=new ArrayList<>();
	
      allposts=(List<Post>)post_rep.findAll();
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {

			String response = "";
for (Post post : allposts) {
	System.out.println("+++++++++++++++++++++++hani houni +++++++++++++++++++++");	
	int nbreact=rea_rep.countreact();
System.out.println(nbreact);
			

				float count_reacttrue = (rea_rep.countreacttrue(post.getId(),true)
						/ rea_rep.countreact()) * 100;
				float count_reactfalse = (rea_rep.countreactfalse(post.getId(),false)
						/ rea_rep.countreact()) * 100;
				
				response = response + "Le post est  " + post.getTitle()
				+ "  le nombre de LIKE est   " + count_reacttrue + "  % "
				+ "  le nombre de DISLIKE est   " + count_reactfalse + "  % ";
			
		}
			return ResponseEntity.ok(new MessageResponse(response));

		}

		return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));
	}
	
	//****************************************StatPost******************************************************//

}

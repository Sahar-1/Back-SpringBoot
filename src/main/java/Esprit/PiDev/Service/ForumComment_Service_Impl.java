package Esprit.PiDev.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Bad_Words;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.ForumComment;
import Esprit.PiDev.Entity.ForumSubject;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Forum_Comment_Repository;
import Esprit.PiDev.Repository.Forum_Subject_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.dto.CommentDto;

@Service
public class ForumComment_Service_Impl {

	@Autowired
	Forum_Comment_Repository forumRepo;
	@Autowired
	User_Repository usrep;
	@Autowired
	Forum_Subject_Repository frep;
	@Autowired
	User_Repository ur1;

	@Autowired
	TempUserService userService;

	@Autowired
	Email_Sender_Service emailSenderService;

	public void addForumCommentToSubject(ForumComment comment,Long idsubject) {
		Dbo_User dbo_User = userService.getConnectedUser();
		List<String> list_badwords = new ArrayList<>();
		// Dbo_User dbo_User = ur1.findById(iduser).orElse(null);
		// if (dbo_User.getRole().stream().anyMatch(e ->
		// e.getName().equals(ERole.ROLE_PARENT))) {
		for (Bad_Words bad : Bad_Words.values()) {
			if (comment.getAnswer().contains(bad.toString())) {
				list_badwords.add(comment.getAnswer());
			}
		}
		if (list_badwords.size() == 0) {

			// Dbo_User dbo_User1 = usrep.findById(iduser).orElse(null);
			ForumSubject subject = frep.findById(idsubject).orElse(null);
			comment.setForumSubject(subject);
			comment.setUser(dbo_User);
			// u.setUser(dbo_User);
			forumRepo.save(comment);
			// return ResponseEntity.ok(new MessageResponse("comment added"));

			 } else {
			//Session_UserDetails userDetails = (Session_UserDetails)
			// authentication.getPrincipal();
			 //Dbo_User user = usrep.findByEmail(userDetails.getEmail());

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(dbo_User.getEmail());
			mailMessage.setSubject("!! bad words Information !!");
			mailMessage.setFrom("ayoub.benzahra@esprit.tn");
			mailMessage.setText(" CHER Mr/mrs votre commentaire etait supprimé vu qu'il contient  " + comment.getAnswer());

			emailSenderService.sendEmail(mailMessage);

			// return ResponseEntity.ok(new MessageResponse("bad words "));
			// }
			// } else {
			// return ResponseEntity.ok(new MessageResponse("user isn't
			// parent"));

		}

	}

	public void updateForum(ForumComment forumComment) {
		Dbo_User dbo_User = userService.getConnectedUser();
		// Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		// if (dbo_User.getRole().stream().anyMatch(e ->
		// e.getName().equals(ERole.ROLE_PARENT))) {

		ForumComment commentToUpdate = forumRepo.findById((long) forumComment.getId()).orElse(null);

		if (commentToUpdate != null) {

			commentToUpdate.setAnswer(forumComment.getAnswer());
			commentToUpdate.setPostedDate(forumComment.getPostedDate());
			forumRepo.save(commentToUpdate);

			// return ResponseEntity.ok(new MessageResponse("Votre commentaire
			// est bien modifiée "));
			// } else {
			// return ResponseEntity.ok(new MessageResponse("Le commentaire
			// n'existe pas"));
			// }

			// } else {
			// return ResponseEntity.ok(new MessageResponse("Vous n'êtes pas
			// parent"));
		}

	}

	public void Retrieve_All_Forum_Comment(/* long user_id */) {
		Dbo_User dbo_User = userService.getConnectedUser();
		// Dbo_User dbo_User = ur1.findById(user_id).orElse(null);

		// if (dbo_User.getRole().stream().anyMatch(e ->
		// e.getName().equals(ERole.ROLE_PARENT)))

		// {
		forumRepo.findAll();

		// }
		// {
		// return ResponseEntity.ok(new MessageResponse("user n'est pas
		// parent"));

		// }
	}

	public int getNombreForum() {
		Dbo_User dbo_User = userService.getConnectedUser();
		return forumRepo.countForumsComments();
	}

	public void retrieveForumComment(/* long user_id, */ Long id) {
		Dbo_User dbo_User = userService.getConnectedUser();
		// Dbo_User dbo_User = ur1.findById(user_id).orElse(null);

		// if (dbo_User.getRole().stream().anyMatch(e ->
		// e.getName().equals(ERole.ROLE_PARENT)))

		// {
		// returnForumRp ResponseEntity.ok(new MessageResponse("" +
		forumRepo.findById(id).get();

		// }
		// {
		// return ResponseEntity.ok(new MessageResponse("user n'est pas
		// admin"));

		// }
	}

	public List<CommentDto> findCommentBySubject(ForumSubject subject) throws Exception {

		Dbo_User dbo_User = userService.getConnectedUser();

		if (!dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			throw new Exception("user n'est pas parent");
		}

		return forumRepo.findCommentBySubject(subject).stream().map(c -> {
			CommentDto dto = new CommentDto();
			dto.setId(c.getId());
			dto.setAnswer(c.getAnswer());
			dto.setPostedDate(c.getPostedDate());
			dto.setUserId(c.getUser().getId());
			dto.setUserFirstName(c.getUser().getFirstName());
			dto.setUserLastName(c.getUser().getLastName());
			return dto;
		}).collect(Collectors.toList());

	}

	public void deleteForum(Long id) {
		Dbo_User dbo_User = userService.getConnectedUser();
		forumRepo.deleteById(id);

	}

	public ResponseEntity<?> chercher_badWords(Long user_id/* , String word */) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			List<ForumComment> list = new ArrayList<ForumComment>();
			List<ForumComment> list_chercher = new ArrayList<ForumComment>();

			int x = 0;

			for (ForumComment comment : forumRepo.findAll()) {
				for (Bad_Words bad : Bad_Words.values()) {
					if (comment.getAnswer().contains(bad.toString())) {
						forumRepo.delete(comment);
					}
				}

			}
			return ResponseEntity.ok(new MessageResponse("les badwords suppprimées"));

		} else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

		}

	}

	public ResponseEntity<?> StatistiqueCommentSubjectbyUser(Long user_id, Long iduserforum, String question) {
		// TODO Auto-generated method stub
		Dbo_User dbo_User = usrep.findById(user_id).orElse(null);
		Dbo_User userforum = usrep.findById(iduserforum).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {

			String response = "";

			for (ForumSubject forum : userforum.getForumSubjects()) {

				float countcom = (forumRepo.countsubjectquetions(forum.getId(), question)
						/ forumRepo.countComment(forum.getId())) * 100;
				response = response + "question est  " + forum.getQuestion() + "  les nombres   sont" + countcom;

			}

			return ResponseEntity.ok(new MessageResponse(response));

		}

		return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));

	}
	/*
	 * public ResponseEntity<?> StatistiqueMaxCommentSubject(Long user_id, Long
	 * idforumSubject) { Dbo_User dbo_User =
	 * usrep.findById(user_id).orElse(null); String response = ""; if
	 * (dbo_User.getRole().stream().anyMatch(e ->
	 * e.getName().equals(ERole.ROLE_ADMIN))) { return
	 * ResponseEntity.ok(ForumRp.countmaxCommentbySubject(idforumSubject));
	 * 
	 * }else {
	 * 
	 * return ResponseEntity.ok(new MessageResponse(response)); }
	 * 
	 * }
	 * 
	 */
}

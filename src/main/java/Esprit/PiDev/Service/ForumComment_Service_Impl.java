package Esprit.PiDev.Service;

import java.util.ArrayList;
import java.util.List;

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
import Esprit.PiDev.InterfaceService.Interface_Forum_Comment_Service;
import Esprit.PiDev.Repository.Forum_Comment_Repository;
import Esprit.PiDev.Repository.Forum_Subject_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class ForumComment_Service_Impl implements Interface_Forum_Comment_Service{
	
	
	@Autowired
	Forum_Comment_Repository ForumRp;
	@Autowired
	User_Repository usrep;
	@Autowired
	Forum_Subject_Repository frep;
	@Autowired
	
	User_Repository ur1;
	
	@Autowired
	Email_Sender_Service  emailSenderService;
	
	
	
@Override
public ResponseEntity<?>   Add_Forum(ForumComment u ,Long idsubject , Long iduser) {
	List<String> list_badwords = new ArrayList<>();
	Dbo_User dbo_User = ur1.findById(iduser).orElse(null);
	if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
		for (Bad_Words bad : Bad_Words.values()) { 
		if(u.getAnswer().contains(bad.toString()))
		{  list_badwords.add(u.getAnswer());
			//return ResponseEntity.ok(new MessageResponse("bad words "));

	
		}
		
	}
		if(list_badwords.size() ==0 )
		{
			
		//	Dbo_User dbo_User1 = usrep.findById(iduser).orElse(null);
			ForumSubject subject = frep.findById(idsubject).orElse(null);
			 u.setForumSubject(subject);
			u.setUser(dbo_User);
			ForumRp.save(u);
			return ResponseEntity.ok(new MessageResponse("comment"));
			
		}
		else
		{
			Dbo_User user = new Dbo_User();
			for (Dbo_User dbo_User2 : dbo_User.getGarden().getUsers()) {
				if (dbo_User2.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {	
					SimpleMailMessage mailMessage = new SimpleMailMessage();
					mailMessage.setTo(dbo_User2.getEmail());
					mailMessage.setSubject("!! bad words Information !!");
					mailMessage.setFrom("ayoub.benzahra@esprit.tn");
					mailMessage.setText(" Dear Mr vous avez creer  "+ u.getAnswer());

					emailSenderService.sendEmail(mailMessage);
					
				}
			}
			
			return ResponseEntity.ok(new MessageResponse("bad words "));
		}
	}
	else
	{
		return ResponseEntity.ok(new MessageResponse("user isn't parent"));

	}
	
		
	

	
	
}


 

	@Override
	public ForumComment Update_Forum(ForumComment C) {
		return ForumRp.save(C);
		
	}

	@Override
	public List<ForumComment> Retrieve_All_Forum() {
		
		return (List<ForumComment>)ForumRp.findAll();
	}



	@Override
	public int getNombreForum() {
		return ForumRp.countForumsComments();
	}



	@Override
	public ForumComment retrieveForum(Long id) {
		return ForumRp.findById(id).get();
	}



	@Override
	public void deleteForum(Long id) {
		ForumRp.deleteById(id);
		
	}
	


	public ResponseEntity<?> chercher_badWords(Long user_id/*, String word*/) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			List<ForumComment> list=new ArrayList<ForumComment>();
			List<ForumComment> list_chercher=new ArrayList<ForumComment>();

			
			int x=0;
			//list=(List<ForumComment>)ForumRp.findAll();
			
		//	for (ForumComment forumComment : list) {
				// Optional<ForumComment> ttt = ForumRp.find
			for(ForumComment comment: ForumRp.findAll())
			{
				for (Bad_Words bad : Bad_Words.values()) { 
				if(comment.getAnswer().contains(bad.toString()))
				{   ForumRp.delete(comment);

				//	System.out.println(bad);
					
				//	list_chercher.add(comment);
				}
				}
				
			}
			return ResponseEntity.ok(new MessageResponse("les badwords suppprimées"));

		/*	if(list_chercher.size() == 0)
			{
				return		ResponseEntity.ok(new MessageResponse("jawak behi "));
			}
			else
			{				
				System.out.println("**************************************");
				

			return		ResponseEntity.ok(new MessageResponse("badwords"));
			}*/
			
				
				
					

				
			/*	
			switch (x) {
			case 1:
				forumComment.getAnswer().equals(Bad_Words.ahed);
				break;
                  case 2:
      				forumComment.getAnswer().equals(Bad_Words.balkis);

				break;
				
                  case 3:
      				forumComment.getAnswer().equals(Bad_Words.ons);

					break;
					
                  case 4:
      				forumComment.getAnswer().equals(Bad_Words.ghassen);

						break;
			default:
				break;
			}
			*/
				
				
			
			/*
			
			if(x!=0)
			{
				return ResponseEntity.ok(new MessageResponse("badwords"));

				
				
				
				
				
			}
			
			
		
		}
*/
		}
		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un badwords"));

		}
	

	}
	
	}




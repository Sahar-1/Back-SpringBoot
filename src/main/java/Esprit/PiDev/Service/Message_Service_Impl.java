package Esprit.PiDev.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Message;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Repository.Message_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Message_Service_Impl  implements Message_Service{
	@Autowired
	private	User_Repository us_rep;
	@Autowired
	private Message_Repository msg_rep;
	
	
@Override
public ResponseEntity<?> sendMessage(Message message,Long sender,Long reciever) {
	// TODO Auto-generated method stub

	 Dbo_User send = us_rep.findById(sender).orElse(null);
	 Dbo_User reciev = us_rep.findById(reciever).orElse(null);
System.out.println("send.getRole()   " +send.getRole());
System.out.println("reciev null  "+reciev.getEmail());
		if (send.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT)) ) {
			

			
			if(reciev!=null)
			{
				
			if ( message.getContent() != null) {
			
				message.setSender(send);
				 Date date =new  Date();
				message.setDate(date);
				message.setReceiver(reciev);
				message.setContent(message.getContent());
				msg_rep.save(message);
		
				return ResponseEntity.ok(new MessageResponse("Votre message a été envoyer a l'utlisateur num "+reciever+"  avec success"));
			} 
			else {
				return ResponseEntity.ok(new MessageResponse("message null "));
			}

	}
		
}

		return ResponseEntity.ok(new MessageResponse("athhh"));
	
	
}

@Override
public ResponseEntity<?> deleteMessage(Message msg,Long user_id) {
	// TODO Auto-generated method stub
	
	 Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			if ( msg != null) {
				
				msg_rep.delete(msg);
		
				return ResponseEntity.ok(new MessageResponse("Message est bien supprimer "));
			} 
			else {
				return ResponseEntity.ok(new MessageResponse("Message n'existe pas"));
			}

	}
		return ResponseEntity.ok(new MessageResponse("athhh"));
}
@Override
public ResponseEntity<?> deleteMessageById(Long user_id,Long id) {
	// TODO Auto-generated method stub
Message msg =msg_rep.findById(id).orElse(null);
	 Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			if(msg != null)
			{
				msg_rep.deleteById(id);
		
				return ResponseEntity.ok(new MessageResponse("Message est bien supprimer "));
			}
				else {
					return ResponseEntity.ok(new MessageResponse("Message n'existe pas"));
				}

	}
		
		
		
		return ResponseEntity.ok(new MessageResponse("athhh"));
}
@Override
public ResponseEntity<?> findMessageById(Long user_id,Long id) {
	// TODO Auto-generated method stub
	Message msg =msg_rep.findById(id).orElse(null);

	 //Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
	//	if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			if ( msg != null) {
				
			
		
				return ResponseEntity.ok(new MessageResponse("Message  "+ msg_rep.findById(id).orElse(null)));
			} 
			else {
				return ResponseEntity.ok(new MessageResponse("Message n'existe pas"));
			}

	//}
	//	return ResponseEntity.ok(new MessageResponse("athhh"));
}
@Override
/*public ResponseEntity<?> */  
public List<Message> retrieveAllMessages(Long user_id) {
	// TODO Auto-generated method stub

	 Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
	//if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			
				
			return (List<Message>)msg_rep.findAll();
	//}
			/*/	return ResponseEntity.ok(new MessageResponse("Message  "+(List<Message>)msg_rep.findAll()));*/
		

	//}
		//return ResponseEntity.ok(new MessageResponse("athhh"));
	//return null;
}

@Override
public ResponseEntity<?> OpenConversation(Long user_id, Long reciever) {
	// TODO Auto-generated method stub
	 Dbo_User sender = us_rep.findById(user_id).orElse(null);
	 Dbo_User reciv = us_rep.findById(reciever).orElse(null);
		return ResponseEntity.ok(new MessageResponse("conversations  "+msg_rep.conversations(sender,reciv)));

/*List<Message> msgsesder=new ArrayList<>();
(List<Message> )msgreciever=new ArrayList<>();

	for (Message message : sender.getMessagesender()) {
		msgsesder.add(message);
	}
	for (Message message : reciv.getMessagereceiver()) {
		msgreciever.add(message);
	}
	List<Message> conversations=new ArrayList<>();
	conversations.addAll(msgreciever);
	conversations.addAll(msgsesder);*/
	 
	 
}


      @Override
      public ResponseEntity<?> searchmessages(Long user_id,String username) {
    	  Dbo_User dbo_User = us_rep.findById(user_id).orElse(null);
    		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {

			return ResponseEntity.ok(new MessageResponse("Messages"+ (List<Message>)msg_rep.searchMessages(username)));

		}
    		return ResponseEntity.ok(new MessageResponse("athhh"));

      
      }
      
}

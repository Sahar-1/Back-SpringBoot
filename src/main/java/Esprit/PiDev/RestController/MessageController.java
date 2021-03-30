package Esprit.PiDev.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Message;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.Service.Message_Service;
import Esprit.PiDev.Service.Session_UserDetails;

@RestController
@RequestMapping("/messages")
public class MessageController {

	/*@MessageMapping("/news")
	@SendTo("/topic/news")
	public String broadcastNews(@Payload String message) {		
	  return message;
	}*/
	//@Autowired
	//private SimpMessagingTemplate template;
	@Autowired
	private Message_Service messageservice;
	@Autowired
	private User_Repository userrepository;
	
	
	//@RequestMapping(value = "/sendMessage/{idReciever}", method = RequestMethod.POST)
	@PostMapping("/sendMessage/{idReciever}")
	public ResponseEntity<?> sendMessage(Authentication auth,@PathVariable("idReciever") Long idReciever, @RequestBody Message message )  {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		
	messageservice.sendMessage(message,userDetails.getId(),idReciever);
	
	
		return ResponseEntity.ok("done");

	}
	   @DeleteMapping("/remove-Message/{Message-id}") 
		public void  removeMessage(Authentication auth,@PathVariable("Message-id") Long id)
		   {
				SecurityContextHolder.getContext().setAuthentication(auth);
				Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
				messageservice.deleteMessageById(id,userDetails.getId());
		   } 
	
	   @GetMapping("/retrieve-all-Messages")
	   List<Message> retrieveallMessages(Authentication auth) {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	          return messageservice.retrieveAllMessages(userDetails.getId());
	      //	return ResponseEntity.ok("done"+ messageservice.retrieveAllMessages(userDetails.getId())); 
	   } 
	   @GetMapping("/retrieve-conversation/{reciever_id}")
	   public ResponseEntity<?> conversation(Authentication auth,@PathVariable("reciever_id") Long reciever_id) {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	          
	      	return ResponseEntity.ok("done"+ messageservice.OpenConversation(userDetails.getId(),reciever_id)); 
	   } 
	   @GetMapping("/findMessageById/{msg_id}")
	   public ResponseEntity<?> findMessageById(Authentication auth,@PathVariable("msg_id") Long msg_id) 
	   {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	      	return ResponseEntity.ok("done"+ messageservice.findMessageById(userDetails.getId(), msg_id)); 
   
	   }
	   @GetMapping("/searchmessages/{username}")
	   public ResponseEntity<?>  searchMessages(Authentication auth,@PathVariable("username") String username)
	   {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
	      	return ResponseEntity.ok("done"+messageservice.searchmessages(userDetails.getId(), username)); 
  
	   }
}

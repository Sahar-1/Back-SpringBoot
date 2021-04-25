package Esprit.PiDev.InterfaceService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Message;

public interface Message_Service {
	
	public ResponseEntity<?> sendMessage(Message message ,Long user_id,Long reciever); 
	/*public ResponseEntity<?> */
	public List<Message> retrieveAllMessages(Long user_id);
	public ResponseEntity<?> findMessageById(Long user_id,Long id);
	public ResponseEntity<?> deleteMessageById(Long id,Long user_id);
	public ResponseEntity<?> deleteMessage(Message msg,Long user_id);
	public ResponseEntity<?> OpenConversation(Long user_id,Long reciever);
	public ResponseEntity<?>  searchmessages(Long user_id,String username);
	
	
}

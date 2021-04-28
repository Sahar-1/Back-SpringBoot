package Esprit.PiDev.ControllerSocket;



import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import Esprit.PiDev.Entity.Message;

@Controller
public class MainController {
	
	
//	@Autowired
 //   Message_Service msg_ser;

	
	
@MessageMapping("/hello")
 @SendTo("/topic/greetings")
 public Message greeting(Message message) throws Exception {
	 //System.out.println(message.getMessage());
	 Date date =new  Date(); 
	 message.setDate(date);	 
//msg_ser.sendMessage(message);
		 
	 
return new Message(message.getContent());
  }

}

package Esprit.PiDev.Entity.RequestApiForm;

 
public class MessageResponse  {
	private String Message;
    
	public MessageResponse(String exception) {
	    this.Message = exception;
	  }
 

	public String getMessage() {
		return Message;
	}

	public void setMessage(String exception) {
		this.Message = exception;
	}

 
	
}

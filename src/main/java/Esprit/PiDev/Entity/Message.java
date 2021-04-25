package Esprit.PiDev.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_Message")
public class Message {
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Message_id")
	    private Long id;
	
	 
    	@Temporal(TemporalType.DATE)
	    private Date date;

    	@Column(name="content", columnDefinition="TEXT")
    	private String content;
    	@JsonIgnore
    	@ManyToOne
    	private Dbo_User sender;
    	@JsonIgnore
    	@ManyToOne
    	private Dbo_User receiver;
 

   

    	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/

		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Date getDate() {
			return date;
		}


		public void setDate(Date date) {
			this.date = date;
		}





		public String getContent() {
			return content;
		}


		public void setContent(String content) {
			this.content = content;
		}


		public Dbo_User getSender() {
			return sender;
		}


		public void setSender(Dbo_User sender) {
			this.sender = sender;
		}


		public Dbo_User getReceiver() {
			return receiver;
		}


		public void setReceiver(Dbo_User receiver) {
			this.receiver = receiver;
		}



		/*-----------------------****Constructors_Object****-------------------------------------*/

		public Message(Long id, Date date, String message, Dbo_User sender, Dbo_User receiver) {
			super();
			this.id = id;
			this.date = date;
			this.content = message;
			this.sender = sender;
			this.receiver = receiver;
		}


		public Message(String message) {
			super();
			this.content = message;
		}





		public Message() {
			super();
		}


		@Override
		public String toString() {
			return "Message [id=" + id + ", date=" + date + ", content=" + content + ", sender=" + sender
					+ ", receiver=" + receiver + "]";
		}





		

		



}

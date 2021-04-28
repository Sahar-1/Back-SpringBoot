package Esprit.PiDev.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int rating;
	
	private String comment;

	
	
	
	
	/*-------------------------------association Appointment et feedback--------------------------------------------------*/
	
	@ManyToOne
	private Appointment appointment;
	
	
	/*-------------------------------association Appointment et feedback--------------------------------------------------*/
	
	public Feedback(int rating, String comment) {
		super();
		this.rating = rating;
		this.comment = comment;
	}
	
	public Feedback() {
		super();
	}
	
	
	



	public Feedback(int rating, String comment, Appointment appointment) {
		super();
		this.rating = rating;
		this.comment = comment;
		this.appointment = appointment;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	
	
	
	

}

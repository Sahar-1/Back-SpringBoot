package Esprit.PiDev.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Historique {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String description;
	
	
	
	
	


	/*-------------------------------association historique et appointment--------------------------------------------------*/
	@ManyToOne
	private Appointment appointment;
	/*-------------------------------association historique et appointment--------------------------------------------------*/


	
	/*-------------------------------association historique et user(parent)--------------------------------------------------*/
//	@ManyToOne
	//private Dbo_User dbo_User;
	/*-------------------------------association historique et user(parent)--------------------------------------------------*/
	
	
	/*-------------------------------association historique et garden--------------------------------------------------*/
//	@ManyToOne
//	private Garden garden;
	
	/*-------------------------------association historique et garden--------------------------------------------------*/

	
	
	public Historique()
	{
		
	}
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Appointment getAppointment() {
		return appointment;
	}




	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	@Override
	public String toString() {
		return "Historique [id=" + id + ", description=" + description + ", appointment=" + appointment + "]";
	}
	public Historique(int id, String description, Appointment appointment) {
		super();
		this.id = id;
		this.description = description;
		this.appointment = appointment;
	}
	
	
	


	


}

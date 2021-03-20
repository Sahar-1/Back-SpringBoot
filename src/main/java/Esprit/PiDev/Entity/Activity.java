package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Activity implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	
	
	
	/*-------------------------------association Activity et Garden--------------------------------------------------*/
		@ManyToOne
	    private Garden garden;
	
	/*-------------------------------association Activity et Garden--------------------------------------------------*/

	
	
	public Activity( String title, String description, Date date) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
	}
	
	public Activity() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

	public Garden getGarden() {
		return garden;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	

	@Override
	public String toString() {
		return "Activity [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date
				+ ", garden=" + garden + "]";
	}

	public Activity(int id, String title, String description, Date date, Garden garden) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.garden = garden;
	}
	
	
	
	
	
	
	
	
	
	
}

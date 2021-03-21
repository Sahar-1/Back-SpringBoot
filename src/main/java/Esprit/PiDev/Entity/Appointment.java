package Esprit.PiDev.Entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String beginhour;
	
	private String endhour;

	
	
	private  String description;
	
	private int status;
	
	
	
	
	/*-------------------------------association Appointment et user--------------------------------------------------*/
	@ManyToOne
	private Dbo_User user;
	/*-------------------------------association Appointment et user--------------------------------------------------*/
	
	/*-------------------------------association Appointment et garden--------------------------------------------------*/
	@ManyToOne
	private Garden garden;
	/*-------------------------------association Appointment et garden--------------------------------------------------*/

	
	
	/*-------------------------------association Appointment et feedback--------------------------------------------------*/
	@OneToMany(mappedBy="appointment",fetch= FetchType.EAGER)
	private Set<Feedback> feedbacks;
	/*-------------------------------association Appointment et feedback--------------------------------------------------*/
	
	
	
	/*-------------------------------association historique et appointment--------------------------------------------------*/
	@OneToMany(mappedBy="appointment",fetch= FetchType.EAGER)
	private Set<Historique> historiques;
	/*-------------------------------association historique et appointment--------------------------------------------------*/

	
	public Appointment() {
		super();
		
	}
	
		public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	



	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}




	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	
	




	public int getStatus() {
		return status;
	}




	public void setStatus(int status) {
		this.status = status;
	}





    public Appointment(String description, int status, Dbo_User user, Garden garden, Set<Feedback> feedbacks) {
		super();
		this.description = description;
		this.status = status;
		this.user = user;
		this.garden = garden;
		this.feedbacks = feedbacks;
	}
    

    public Appointment(String description, int status, Dbo_User user, Garden garden) {
		super();
		this.description = description;
		this.status = status;
		this.user = user;
		this.garden = garden;
	}

	

	

	public Appointment(Date date, String beginhour, String endhour, String description, int status, Dbo_User user,
			Garden garden) {
		super();
		this.date = date;
		this.beginhour = beginhour;
		this.endhour = endhour;
		this.description = description;
		this.status = status;
		this.user = user;
		this.garden = garden;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBeginhour() {
		return beginhour;
	}

	public void setBeginhour(String beginhour) {
		this.beginhour = beginhour;
	}

	public String getEndhour() {
		return endhour;
	}

	public void setEndhour(String endhour) {
		this.endhour = endhour;
	}

	public Dbo_User getUser() {
		return user;
	}

	public void setUser(Dbo_User user) {
		this.user = user;
	}

	public Garden getGarden() {
		return garden;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", beginhour=" + beginhour + ", endhour=" + endhour
				+ ", description=" + description + ", status=" + status + "]";
	}

	public Appointment(Date date, String beginhour, String endhour, String description, int status, Dbo_User user,
			Garden garden, Set<Feedback> feedbacks) {
		super();
		this.date = date;
		this.beginhour = beginhour;
		this.endhour = endhour;
		this.description = description;
		this.status = status;
		this.user = user;
		this.garden = garden;
		this.feedbacks = feedbacks;
	}

	public Set<Historique> getHistoriques() {
		return historiques;
	}

	public void setHistoriques(Set<Historique> historiques) {
		this.historiques = historiques;
	}
	

	
  
	



	
	



	
	
	




	

	
	
	
	
	

	
	
	

}

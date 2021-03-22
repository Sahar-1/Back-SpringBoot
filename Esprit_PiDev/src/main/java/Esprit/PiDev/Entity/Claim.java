package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_Claim")

public class Claim  implements Serializable{
private static final long serialVersionUID = 1L;
	
	/*-----------------------****Bean_Attributes****   *********-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Claim_id")
	private Long id;
    
	private  String claimname;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getClaimname() {
		return claimname;
	}
	public void setClaimname(String claimname) {
		this.claimname = claimname;
	}
	@Temporal(TemporalType.DATE)
	private Date Date;
	
	public Claim(Long id, String claimname, java.util.Date date, Esprit.PiDev.Entity.Priorty priorty, String state,
			String discription, int rating, Garden garden, Dbo_User user) {
		super();
		this.id = id;
		this.claimname = claimname;
		Date = date;
		Priorty = priorty;
		this.state = state;
		Discription = discription;
		Rating = rating;
		this.garden = garden;
		this.user = user;
	}
	public void setPriorty(Priorty priorty) {
		Priorty = priorty;
	}
	@Enumerated(EnumType.STRING)
	@Column(name = "Priorty")
	private Priorty Priorty;
	
	private String state;
	@Column(columnDefinition="varchar(1000)",name="discription")
	private String Discription;
	public Claim(String discription) {
		super();
		Discription = discription;
	}

	@Column(name="rating")
	private int Rating ;
	public  Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public Priorty getPriorty() {
		return Priorty;
	}
	
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	
	
	public String getDiscription() {
		return Discription;
	}
	public void setDiscription(String discription) {
		Discription = discription;
	}
	public int getRating() {
		return Rating;
	}
	public void setRating(int rating) {
		Rating = rating;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	


	public Claim(String claimname, java.util.Date date, Esprit.PiDev.Entity.Priorty priorty, String discription,
			int rating, Garden garden, Dbo_User user) {
		super();
		this.claimname = claimname;
		this.Date = date;
		this.Priorty = priorty;
		this.Discription = discription;
		this.Rating = rating;
		this.garden = garden;
		this.user = user;
	}
	
	
	public Claim() {
		super();
	}
	
	
	
	
	
	
	@ManyToOne 
//	@JoinColumn(name="idGarden",  insertable=false, updatable=false)
	private Garden  garden;
	
	@ManyToOne
//@JoinColumn(name="idUser",   insertable=false, updatable=false)
	private Dbo_User user;

	public Garden getGarden() {
		return garden;
	}
	public void setGarden(Garden garden) {
		this.garden = garden;
	}
	public Dbo_User getUser() {
		return user;
	}
	public void setUser(Dbo_User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Claim [id=" + id + ", claimname=" + claimname + ", Date=" + Date + ", Priorty=" + Priorty
				+ ", Discription=" + Discription + ", Rating=" + Rating + ", garden=" + garden + ", user=" + user + "]";
	}
	
	
	
	
	
	
}
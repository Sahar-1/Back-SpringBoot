package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "T_Claim")

public class Claim  implements Serializable{
private static final long serialVersionUID = 1L;
	
	/*-----------------------****Bean_Attributes****   *********-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Claim_id")
	private Long id;
	@Temporal(TemporalType.DATE)
	private Date Date=Calendar.getInstance().getTime();
	@Enumerated(EnumType.STRING)
	@Column(name = "Priorty")
	private Priorty Priorty;
	@Enumerated(EnumType.STRING)
	@Column(name = "Claim_category")
	private Claim_category Claim_category;
	private String state;
	@Column(columnDefinition="varchar(1000)",name="discription")
	private String Discription;
	@Column(name="rating")
	private int Rating ;
	private  String claimname;
	@JsonIgnore
	@ManyToOne 
	private Garden  garden;
	
	@JsonIgnore
	@ManyToOne
	private Dbo_User user;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "recipient_id")
	private Dbo_User recipient;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	
	public Claim_category getClaim_category() {
		return Claim_category;
	}
	public void setClaim_category(Claim_category claim_category) {
		Claim_category = claim_category;
	}
	public Priorty getPriorty() {
		return Priorty;
	}
	public void setPriorty(Priorty priorty) {
		Priorty = priorty;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getClaimname() {
		return claimname;
	}
	public void setClaimname(String claimname) {
		this.claimname = claimname;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Claim(Long id, java.util.Date date, Esprit.PiDev.Entity.Priorty priorty,
			Esprit.PiDev.Entity.Claim_category claim_category, String state, String discription, int rating,
			String claimname, Garden garden, Dbo_User user) {
		super();
		this.id = id;
		Date = date;
		Priorty = priorty;
		Claim_category = claim_category;
		this.state = state;
		Discription = discription;
		Rating = rating;
		this.claimname = claimname;
		this.garden = garden;
		this.user = user;
	}
	public Claim(Long id, java.util.Date date, Priorty priorty, String state, String discription,
			int rating, String claimname, Garden garden, Dbo_User user) {
		super();
		this.id = id;
		Date = date;
		Priorty = priorty;
		this.state = state;
		Discription = discription;
		Rating = rating;
		this.claimname = claimname;
		this.garden = garden;
		this.user = user;
	}
	public Claim() {
		super();
	}
	@Override
	public String toString() {
		return "Claim [id=" + id + ", Date=" + Date + ", Priorty=" + Priorty + ", state=" + state + ", Discription="
				+ Discription + ", Rating=" + Rating + ", claimname=" + claimname + ", garden=" + garden + ", user="
				+ user + "]";
	}

	
	
	
}
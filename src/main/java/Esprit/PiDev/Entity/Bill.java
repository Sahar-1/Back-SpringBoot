package Esprit.PiDev.Entity;

import java.io.Serializable;
 import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")

public class Bill implements Serializable{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Bill_id")
	private Long id;
	
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateStart;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDeadline;
	  

	@Column(columnDefinition="varchar(1000)",name="discription")
	private String discription;
	
	
	private double total;
	
	private double ttc;
	private float discount ;
	
	
	/*-------------------------------association bill and user--------------------------------------------------*/
	
	 @ManyToOne/*(fetch=FetchType.EAGER)*/
	 Dbo_User user;
	 
	/*-------------------------------association bill and garden--------------------------------------------------*/
	 
	
	 @ManyToOne(fetch=FetchType.EAGER)
	 Garden garden;

	 
	public Garden getGarden() {
		return garden;
	}


	public void setGarden(Garden garden) {
		this.garden = garden;
	}


	/*-----------------------****Constructors_Object****-------------------------------------*/
	public Bill() { 
		super();
	}


	public Bill(Long id, Date dateStart, Date dateDeadline, String discription, double total, double ttc
			, String title ) {
		super();
		this.id = id;
		this.dateStart = dateStart;
		this.dateDeadline = dateDeadline;
		this.discription = discription;
		this.total = total;
		this.ttc = ttc;
		this.title=title;
 
	}


 
	

	public Bill(Long id, Date dateStart, Date dateDeadline, String discription, double total, double ttc, String title  ,
			float discount ) {
		super();
		this.id = id;
		this.dateStart = dateStart;
		this.dateDeadline = dateDeadline;
		this.discription = discription;
		this.total = total;
		this.ttc = ttc;
		this.discount = discount;
		this.title=title;
		 
	}
	


	public Bill(Long id, String title, Date dateStart, Date dateDeadline, String discription, double total, double ttc,
			float discount, Dbo_User user) {
		super();
		this.id = id;
		this.title = title;
		this.dateStart = dateStart;
		this.dateDeadline = dateDeadline;
		this.discription = discription;
		this.total = total;
		this.ttc = ttc;
		this.discount = discount;
		this.user=user;
	}


	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/
	
	
	public Bill(Long id, String title, Date dateStart, Date dateDeadline, String discription, double total, double ttc,
			float discount, Dbo_User user, Garden garden) {
		super();
		this.id = id;
		this.title = title;
		this.dateStart = dateStart;
		this.dateDeadline = dateDeadline;
		this.discription = discription;
		this.total = total;
		this.ttc = ttc;
		this.discount = discount;
		
	}


	public Long getId() {
		return id;
	}


	public Dbo_User getUser() {
		return user;
	}


	public void setUser(Dbo_User user) {
		this.user = user;
	}


	


	public float getDiscount() {
		return discount;
	}


	public void setDiscount(float discount) {
		this.discount = discount;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDateStart() {
		return dateStart;
	}


	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}


	public Date getDateDeadline() {
		return dateDeadline;
	}


	public void setDateDeadline(Date dateDeadline) {
		this.dateDeadline = dateDeadline;
	}


	public String getDiscription() {
		return discription;
	}


	public void setDiscription(String discription) {
		this.discription = discription;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public double getTtc() {
		return ttc;
	}


	public void setTtc(double ttc) {
		this.ttc = ttc;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	

	


	 

	
	

	/*-----------------------****TO_String()****-------------------------------------*/
	
	
	 
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}

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
import javax.persistence.Transient;

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
	private double amount;
	private float discount ;
	
	
	@Transient
	private String userpk;
	@Transient
	private String gardenpk;
	@Transient
	private String amount_payed;
	@Transient
	private String amount_not_payed;


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



	public Bill(Long id, String title, Date dateStart, Date dateDeadline, String discription, double total,
			double amount, float discount, Dbo_User user, Garden garden) {
		super();
		this.id = id;
		this.title = title;
		this.dateStart = dateStart;
		this.dateDeadline = dateDeadline;
		this.discription = discription;
		this.total = total;
		this.amount = amount;
		this.discount = discount;
		this.user = user;
		this.garden = garden;
	}



	public Bill(Long id, String title, Date dateStart, Date dateDeadline, String discription, double total, double amount,
			float discount, String userpk, String gardenpk, Dbo_User user, Garden garden) {
		super();
		this.id = id;
		this.title = title;
		this.dateStart = dateStart;
		this.dateDeadline = dateDeadline;
		this.discription = discription;
		this.total = total;
		this.amount = amount;
		this.discount = discount;
		this.userpk = userpk;
		this.gardenpk = gardenpk;
		this.user = user;
		this.garden = garden;
	}


	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/
	
	





	public Long getId() {
		return id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getAmount_payed() {
		return amount_payed;
	}


	public void setAmount_payed(String amount_payed) {
		this.amount_payed = amount_payed;
	}


	public String getAmount_not_payed() {
		return amount_not_payed;
	}


	public void setAmount_not_payed(String amount_not_payed) {
		this.amount_not_payed = amount_not_payed;
	}


	public Dbo_User getUser() {
		return user;
	}


	public void setUser(Dbo_User user) {
		this.user = user;
	}


	


	public double getamount() {
		return amount;
	}


	public void setamount(double amount) {
		this.amount = amount;
	}


	public String getUserpk() {
		return userpk;
	}


	public void setUserpk(String userpk) {
		this.userpk = userpk;
	}


	public String getGardenpk() {
		return gardenpk;
	}


	public void setGardenpk(String gardenpk) {
		this.gardenpk = gardenpk;
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




	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	

	


	 

	
	

	/*-----------------------****TO_String()****-------------------------------------*/
	
	@Override
	public String toString() {
		return "Bill [id=" + id + ", title=" + title + ", dateStart=" + dateStart + ", dateDeadline=" + dateDeadline
				+ ", discription=" + discription + ", total=" + total + ", amount=" + amount + ", discount=" + discount
				+ ", user=" + user + ", garden=" + garden + "]";
	}

	 
	
	

	
	
	
	
	
	
	
	
	
	
	
	
}

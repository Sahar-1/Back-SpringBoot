package Esprit.PiDev.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDto {

	private Long id;
	private String title;
	@JsonFormat(pattern ="yyyy-MM-dd" ,shape =Shape.STRING)
	private Date dateStart;
	@JsonFormat(pattern ="yyyy-MM-dd" ,shape =Shape.STRING)
	private Date dateDeadline;
	private String discription;
	private double total;
	private double amount;
	private float discount;

	private String userpk;
	private String gardenpk;
	private Long amount_payed;
	private Long amount_not_payed;
	
	

	private Long userId;
	private String userFirstName;
	private String userLastName;

	private Long gardenId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public Long getGardenId() {
		return gardenId;
	}
	public void setGardenId(Long gardenId) {
		this.gardenId = gardenId;
	}
	public String getGardenName() {
		return gardenName;
	}
	public void setGardenName(String gardenName) {
		this.gardenName = gardenName;
	}
	private String gardenName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
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
	public Long getAmount_payed() {
		return amount_payed;
	}
	public void setAmount_payed(Long amount_payed) {
		this.amount_payed = amount_payed;
	}
	public Long getAmount_not_payed() {
		return amount_not_payed;
	}
	public void setAmount_not_payed(Long amount_not_payed) {
		this.amount_not_payed = amount_not_payed;
	}


	

}

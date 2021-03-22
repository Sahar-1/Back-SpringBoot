package Esprit.PiDev.Entity;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_Contract")
public class Contract {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Contract_id")
	private Long id;
	@Column(name = "Title")
	private String Title;
	@Column(name = "NameKinderGarten")
	private String NameKinderGarten;
	@Enumerated(EnumType.STRING)
	@Column(name = "Type")
	private Type_Contrat Type;
	@Temporal(TemporalType.DATE)
	private Date Date_Start;
	@Temporal(TemporalType.DATE)
	private Date Date_deadline;
	@Column(name = "Prix")
	private float Prix;
	@Column(name = "paidOrNot")
	private boolean paidOrNot;
	private subscription subscription;
	@Column(columnDefinition = "varchar(1000)", name = "discription")
	private String Discription;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Garden_id", referencedColumnName = "id")
	private Garden Garden;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Plan_id", referencedColumnName = "id")
	private Plan Plan;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getNameKinderGarten() {
		return NameKinderGarten;
	}
	public void setNameKinderGarten(String nameKinderGarten) {
		NameKinderGarten = nameKinderGarten;
	}
	public Type_Contrat getType() {
		return Type;
	}
	public void setType(Type_Contrat type) {
		Type = type;
	}
	public Date getDate_Start() {
		return Date_Start;
	}
	public void setDate_Start(Date date_Start) {
		Date_Start = date_Start;
	}
	public Date getDate_deadline() {
		return Date_deadline;
	}
	public void setDate_deadline(Date date_deadline) {
		Date_deadline = date_deadline;
	}
	public float getPrix() {
		return Prix;
	}
	public void setPrix(float prix) {
		Prix = prix;
	}
	public boolean isPaidOrNot() {
		return paidOrNot;
	}
	public void setPaidOrNot(boolean paidOrNot) {
		this.paidOrNot = paidOrNot;
	}
	public subscription getSubscription() {
		return subscription;
	}
	public void setSubscription(subscription subscription) {
		this.subscription = subscription;
	}
	public String getDiscription() {
		return Discription;
	}
	public void setDiscription(String discription) {
		Discription = discription;
	}
	public Garden getGarden() {
		return Garden;
	}
	public void setGarden(Garden garden) {
		Garden = garden;
	}
	public Plan getPlan() {
		return Plan;
	}
	public void setPlan(Plan plan) {
		Plan = plan;
	}
	public Contract(Long id, String title, String nameKinderGarten, Type_Contrat type, Date date_Start,
			Date date_deadline, float prix, boolean paidOrNot, Esprit.PiDev.Entity.subscription subscription,
			String discription, Esprit.PiDev.Entity.Garden garden, Esprit.PiDev.Entity.Plan plan) {
		super();
		this.id = id;
		Title = title;
		NameKinderGarten = nameKinderGarten;
		Type = type;
		Date_Start = date_Start;
		Date_deadline = date_deadline;
		Prix = prix;
		this.paidOrNot = paidOrNot;
		this.subscription = subscription;
		Discription = discription;
		Garden = garden;
		Plan = plan;
	}
	public Contract() {
		super();
	}
	@Override
	public String toString() {
		return "Contract [id=" + id + ", Title=" + Title + ", NameKinderGarten=" + NameKinderGarten + ", Type=" + Type
				+ ", Date_Start=" + Date_Start + ", Date_deadline=" + Date_deadline + ", Prix=" + Prix + ", paidOrNot="
				+ paidOrNot + ", subscription=" + subscription + ", Discription=" + Discription + ", Garden=" + Garden
				+ ", Plan=" + Plan + "]";
	}
	
	

}

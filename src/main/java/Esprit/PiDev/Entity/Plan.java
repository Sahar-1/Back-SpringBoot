package Esprit.PiDev.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_Plan")

public class Plan {
	
	  @Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom ;
	//private  List<pack> packs ; 
	private type_plan type_plan  ;
	private String description ;
	private float offre ; 
	private float prix=100;
	@Temporal(TemporalType.DATE)
	private  Date Date_begin_offre;
	@Temporal(TemporalType.DATE)
	private  Date Date_deadline_offre;
	@OneToOne(mappedBy = "Plan")
    private Contract Contract;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public type_plan getType_plan() {
		return type_plan;
	}
	public void setType_plan(type_plan type_plan) {
		this.type_plan = type_plan;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getOffre() {
		return offre;
	}
	public void setOffre(float offre) {
		this.offre = offre;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public Date getDate_begin_offre() {
		return Date_begin_offre;
	}
	public void setDate_begin_offre(Date date_begin_offre) {
		Date_begin_offre = date_begin_offre;
	}
	public Date getDate_deadline_offre() {
		return Date_deadline_offre;
	}
	public void setDate_deadline_offre(Date date_deadline_offre) {
		Date_deadline_offre = date_deadline_offre;
	}
	public Contract getContract() {
		return Contract;
	}
	public void setContract(Contract contract) {
		Contract = contract;
	}
	public Plan(Long id, String nom,type_plan type_plan, String description, float offre,
			float prix, Date date_begin_offre, Date date_deadline_offre, Contract contract) {
		super();
		this.id = id;
		this.nom = nom;
		this.type_plan = type_plan;
		this.description = description;
		this.offre = offre;
		this.prix = prix;
		Date_begin_offre = date_begin_offre;
		Date_deadline_offre = date_deadline_offre;
		Contract = contract;
	}
	public Plan() {
		super();
	}
	@Override
	public String toString() {
		return "Plan [id=" + id + ", nom=" + nom + ", type_plan=" + type_plan + ", description=" + description
				+ ", offre=" + offre + ", prix=" + prix + ", Date_begin_offre=" + Date_begin_offre
				+ ", Date_deadline_offre=" + Date_deadline_offre + ", Contract=" + Contract + "]";
	}
	
	
	
	
}

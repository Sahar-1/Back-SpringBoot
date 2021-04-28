package Esprit.PiDev.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Trajet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String begintrajet;
	
	

	/*-------------------------------association trajet et user(chauffeur)--------------------------------------------------*/
	@ManyToOne
	private Dbo_User dbo_User;
	
	/*-------------------------------association trajet et user(chauffeur)--------------------------------------------------*/

	
	
	/*-------------------------------association trajet et garden--------------------------------------------------*/
	@ManyToOne
	private Garden garden;
	/*-------------------------------association trajet et garden--------------------------------------------------*/
	
	
	
	
	/*-------------------------------association trajet et bus--------------------------------------------------*/
	@ManyToOne
	private Bus bus;
	/*-------------------------------association trajet et bus--------------------------------------------------*/
	
	
	/*-------------------------------association trajet et classe--------------------------------------------------*/
	@ManyToOne
	private Classe classe;
	/*-------------------------------association trajet et classe--------------------------------------------------*/

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBegintrajet() {
		return begintrajet;
	}

	public void setBegintrajet(String begintrajet) {
		this.begintrajet = begintrajet;
	}


	public Dbo_User getDbo_User() {
		return dbo_User;
	}

	public void setDbo_User(Dbo_User dbo_User) {
		this.dbo_User = dbo_User;
	}

	public Garden getGarden() {
		return garden;
	}

	public void setGarden(Garden garden) {
		this.garden = garden;
	}

	public Trajet(String begintrajet, Dbo_User dbo_User, Garden garden) {
		super();
		this.begintrajet = begintrajet;
		this.dbo_User = dbo_User;
		this.garden = garden;
	}
	
	
	public Trajet() {
		
	}
	
	public Trajet(String begintrajet) {
		super();
		this.begintrajet = begintrajet;
	}

	@Override
	public String toString() {
		return "Trajet [id=" + id + ", begintrajet=" + begintrajet + ", endtrajet=" + "]";
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Trajet( Date date, String begintrajet, Dbo_User dbo_User, Garden garden) {
		super();
		this.date = date;
		this.begintrajet = begintrajet;
		this.dbo_User = dbo_User;
		this.garden = garden;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	

}

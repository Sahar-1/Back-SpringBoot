package Esprit.PiDev.Entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Classe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	
	private  int capacite =30;
	
	
	private String  name;
	
	
	
	private String description;
	@Column(name = "compteur",nullable =true)
	private int  compteur ;

	/*-------------------------------association Classe et user--------------------------------------------------*/

	
	@OneToMany(mappedBy="classe",fetch=FetchType.EAGER)
	private  Set<Dbo_User> list_users;

	/*-------------------------------association Classe et user--------------------------------------------------*/


	
	/*-------------------------------association Classe et garden--------------------------------------------------*/
	@ManyToOne
	private  Garden garden;
	/*-------------------------------association Classe et garden--------------------------------------------------*/

	
	/*-------------------------------association trajet et classe--------------------------------------------------*/
	@OneToMany(mappedBy="classe",fetch=FetchType.EAGER)
	private Set<Trajet> trajets;
	
	/*-------------------------------association trajet et classe--------------------------------------------------*/



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}
	






	public int getCapacite() {
		return capacite;
	}



	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Set<Dbo_User> getList_users() {
		return list_users;
	}



	public void setList_users(Set<Dbo_User> list_users) {
		this.list_users = list_users;
	}
	
	
	
	



	
	

	public Garden getGarden() {
		return garden;
	}



	public void setGarden(Garden garden) {
		this.garden = garden;
	}



	@Override
	public String toString() {
		return "Classe [id=" + id + ", capacite=" + capacite + ", name=" + name + ", description=" + description
				+ ", compteur=" + compteur + ", list_users=" + list_users + ", garden=" + garden + "]";
	}



	public Classe() {
		super();
	}



	public Classe(int capacite, String name, String description, Set<Dbo_User> list_users, Garden garden) {
		super();
		this.capacite = capacite;
		this.name = name;
		this.description = description;
		this.list_users = list_users;
		this.garden = garden;

	}
	
	public Classe(int capacite, String name, String description,  Garden garden) {
		super();
		this.capacite = capacite;
		this.name = name;
		this.description = description;
		this.garden = garden;
	}
	public Classe(int capacite, String name, String description) {
		super();
		this.capacite = capacite;
		this.name = name;
		this.description = description;

	}



	public int getCompteur() {
		return compteur;
	}



	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}



	public Set<Trajet> getTrajets() {
		return trajets;
	}



	public void setTrajets(Set<Trajet> trajets) {
		this.trajets = trajets;
	}
	
	




	
	





	
	
	
}

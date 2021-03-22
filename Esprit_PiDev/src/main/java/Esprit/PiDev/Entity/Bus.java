package Esprit.PiDev.Entity;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Bus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String name;
	
	
	/*-------------------------------association bus et garden--------------------------------------------------*/
	@ManyToOne
	private Garden garden;
	/*-------------------------------association bus et garden--------------------------------------------------*/

	
	/*-------------------------------association trajet et bus--------------------------------------------------*/
	@OneToMany(mappedBy="bus",fetch=FetchType.EAGER)
	private Set<Trajet> trajets;
	/*-------------------------------association trajet et bus--------------------------------------------------*/
	


	
	public Bus(String name, Garden garden) {
		super();
		this.name = name;
		this.garden = garden;
	}
	
	
	public Bus() {
		super();
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Garden getGarden() {
		return garden;
	}


	public void setGarden(Garden garden) {
		this.garden = garden;
	}


	@Override
	public String toString() {
		return "Bus [id=" + id + ", name=" + name + ", garden=" + garden + "]";
	}


	public Set<Trajet> getTrajets() {
		return trajets;
	}


	public void setTrajets(Set<Trajet> trajets) {
		this.trajets = trajets;
	}
	
	
	
	
	
	

}

package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Garden implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private String location;
	private int phone;
	private String email;
	private String name;
	private double price;

	
	
			
	
/*-------------------------------association Garden et user--------------------------------------------------*/
	
	@OneToMany(mappedBy ="garden",fetch=FetchType.EAGER)
	private Set<Dbo_User>  users;
	/*-------------------------------association Garden et user--------------------------------------------------*/
	
	
/*-------------------------------association Garden et classe--------------------------------------------------*/
	
	@OneToMany(mappedBy ="garden",fetch=FetchType.EAGER)
	private Set<Classe>  classes;
	/*-------------------------------association Garden et classe--------------------------------------------------*/
	
	
/*-------------------------------association Garden et appointment--------------------------------------------------*/
	
	@OneToMany(mappedBy ="garden",fetch=FetchType.EAGER)
	private Set<Appointment>  appointments;
	/*-------------------------------association Garden et appointment--------------------------------------------------*/
	
	
	
	
	/*-------------------------------association Activity et Garden--------------------------------------------------*/
	
	@OneToMany(mappedBy="garden",fetch=FetchType.EAGER)
	private Set<Activity> activities;
	
	
	
	/*-------------------------------association Activity et Garden--------------------------------------------------*/

	
	
	/*-------------------------------association trajet et garden--------------------------------------------------*/

	@OneToMany(mappedBy="garden",fetch=FetchType.EAGER)
	private Set<Trajet> trajets;
	
	/*-------------------------------association trajet et garden--------------------------------------------------*/
	


	
	
	/*-------------------------------association bus et garden--------------------------------------------------*/
	@OneToMany(mappedBy="garden",fetch=FetchType.EAGER)
	private Set<Bus> bus;
	
	/*-------------------------------association bus et garden--------------------------------------------------*/

	
	
	public Garden()
	{
		
	}
	
	public Garden(String description, String location, int phone, String email) {
		super();
		this.description = description;
		this.location = location;
		this.phone = phone;
		this.email = email;
	}

	public Garden(String location, int phone, String email) {
		super();
		this.location = location;
		this.phone = phone;
		this.email = email;
	}
	
	

	public Garden(String description, String location, int phone, String email, String name, double price,
			Set<Dbo_User> users, Set<Classe> classes) {
		super();
		this.description = description;
		this.location = location;
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.price = price;
		this.users = users;
		this.classes = classes;
	}
	
	public Garden(String description, String location, int phone, String email, String name, double price) {
		super();
		this.description = description;
		this.location = location;
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.price = price;
	}
	public Garden(String description, String location, int phone, String email, String name) {
		super();
		this.description = description;
		this.location = location;
		this.phone = phone;
		this.email = email;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	

	public Set<Dbo_User> getUsers() {
		return users;
	}

	public void setUsers(Set<Dbo_User> users) {
		this.users = users;
	}

	public Set<Classe> getClasses() {
		return classes;
	}

	public void setClasses(Set<Classe> classes) {
		this.classes = classes;
	}
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	
	
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		this.appointments = appointments;
	}

	
	
	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	@Override
	public String toString() {
		return "Garden [id=" + id + ", description=" + description + ", location=" + location + ", phone=" + phone
				+ ", email=" + email + ", users=" + users + "]";
	}

	public Set<Trajet> getTrajets() {
		return trajets;
	}

	public void setTrajets(Set<Trajet> trajets) {
		this.trajets = trajets;
	}


	public Set<Bus> getBus() {
		return bus;
	}

	public void setBus(Set<Bus> bus) {
		this.bus = bus;
	}
	
	
	
	
	
	
	
	
	

}

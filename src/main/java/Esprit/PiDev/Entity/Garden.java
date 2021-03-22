package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Garden implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String description;
	private String location;
	private int phone;
	private String email;
	private String name;
	private double price;

	@ManyToOne
	ForumSubject forumSubject;
	/*-------------------------------association Garden and bill--------------------------------------------------*/

	@OneToMany(mappedBy = "garden")
	List<Bill> bills;
	@OneToOne(mappedBy = "Garden")
	private Contract Contract;

	/*-------------------------------association Garden et user--------------------------------------------------*/

	@OneToMany(mappedBy = "garden", fetch = FetchType.EAGER)
	private Set<Dbo_User> users;
	/*-------------------------------association Garden et user--------------------------------------------------*/

	/*-------------------------------association Garden et classe--------------------------------------------------*/

	@OneToMany(mappedBy = "garden", fetch = FetchType.EAGER)
	private Set<Classe> classes;
	/*-------------------------------association Garden et classe--------------------------------------------------*/
	@OneToMany(mappedBy = "garden")
	private Set<Claim> claims;

	/*-------------------------------association Garden et appointment--------------------------------------------------*/

	@OneToMany(mappedBy = "garden", fetch = FetchType.EAGER)
	private Set<Appointment> appointments;
	/*-------------------------------association Garden et appointment--------------------------------------------------*/

	/*-------------------------------association Activity et Garden--------------------------------------------------*/

	public Contract getContract() {
		return Contract;
	}

	public void setContract(Contract contract) {
		Contract = contract;
	}

	public Set<Claim> getClaims() {
		return claims;
	}

	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}

	@OneToMany(mappedBy = "garden", fetch = FetchType.EAGER)
	private Set<Activity> activities;

	/*-------------------------------association Activity et Garden--------------------------------------------------*/

	/*-------------------------------association trajet et garden--------------------------------------------------*/

	@OneToMany(mappedBy = "garden", fetch = FetchType.EAGER)
	private Set<Trajet> trajets;

	/*-------------------------------association trajet et garden--------------------------------------------------*/

	/*-------------------------------association bus et garden--------------------------------------------------*/
	@OneToMany(mappedBy = "garden", fetch = FetchType.EAGER)
	private Set<Bus> bus;

	/*-------------------------------association bus et garden--------------------------------------------------*/

	public Garden() {

	}

	public ForumSubject getForumSubject() {
		return forumSubject;
	}

	public void setForumSubject(ForumSubject forumSubject) {
		this.forumSubject = forumSubject;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
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

	@Override
	public String toString() {
		return "Garden [id=" + id + ", description=" + description + ", location=" + location + ", phone=" + phone
				+ ", email=" + email + ", name=" + name + ", price=" + price + ", forumSubject=" + forumSubject
				+ ", bills=" + bills + ", users=" + users + ", classes=" + classes + ", appointments=" + appointments
				+ ", activities=" + activities + ", trajets=" + trajets + ", bus=" + bus + "]";
	}

	public Garden(Long id, String description, String location, int phone, String email, String name, double price,
			ForumSubject forumSubject, List<Bill> bills, Set<Dbo_User> users, Set<Classe> classes,
			Set<Appointment> appointments, Set<Activity> activities, Set<Trajet> trajets, Set<Bus> bus) {
		super();
		this.id = id;
		this.description = description;
		this.location = location;
		this.phone = phone;
		this.email = email;
		this.name = name;
		this.price = price;
		this.forumSubject = forumSubject;
		this.bills = bills;
		this.users = users;
		this.classes = classes;
		this.appointments = appointments;
		this.activities = activities;
		this.trajets = trajets;
		this.bus = bus;
	}

}

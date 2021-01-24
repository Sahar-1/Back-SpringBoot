package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

@Entity
@Table(name = "T_User")
public class Dbo_User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_id")
	private Long id;

	@NotNull
	@Column(name = "User_First_Name")
	private String firstName;

	@NotNull
	@Column(name = "User_Last_Name")
	private String lastName;

	@NotNull
	@Column(columnDefinition = "boolean default false")
	private boolean actif;

	@NotNull
	@Column(name = "User_Birthday_Date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@NotNull
	@Column(name = "User_Email")
	private String email;

	@NotNull
	@Column(name = "User_Password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Dbo_Role role;

	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public Dbo_Role getRole() {
		return role;
	}

	public void setRole(Dbo_Role role) {
		this.role = role;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*-----------------------****Constructors_Object****-------------------------------------*/
	public Dbo_User() {
		super();
	}

	public Dbo_User(Long id, String firstName, String lastName, boolean actif, Date date, String email,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.actif = actif;
		this.date = date;
		this.email = email;
		this.password = password;
	}

	public Dbo_User(String firstName, String lastName, boolean actif, Date date, String email, String password,
			Dbo_Role role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.actif = actif;
		this.date = date;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Dbo_User(String firstName, String lastName, boolean actif, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.actif = actif;
		this.email = email;
		this.password = password;
	}

	public Dbo_User(String email, String password) {
		super();
		this.password = password;
	}

	/*-----------------------****TO_String()****-------------------------------------*/
	@Override
	public String toString() {
		return "Dbo_User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", actif=" + actif
				+ ", date=" + date + ", email=" + email + ", password=" + password + "]";
	}
	/*------------------------------------------------------------*/

}

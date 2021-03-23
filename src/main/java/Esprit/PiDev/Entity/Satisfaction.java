package Esprit.PiDev.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_Satisfaction")
public class Satisfaction {
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Satisfaction_id")
	private Long id;
	@Column(name = "name")
    private String name;
	@Temporal(TemporalType.DATE)	  
    private Date Satisfaction_Date;
	@ManyToMany
	private List<Dbo_User> users;
    @OneToMany(mappedBy="satisfactions",fetch=FetchType.EAGER)
	private List<Question_Satisfaction> questions;

	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getSatisfaction_Date() {
		return Satisfaction_Date;
	}
	public void setSatisfaction_Date(Date satisfaction_Date) {
		Satisfaction_Date = satisfaction_Date;
	}
	
	public List<Question_Satisfaction> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question_Satisfaction> questions) {
		this.questions = questions;
	}
	
	public List<Dbo_User> getUsers() {
		return users;
	}
	public void setUsers(List<Dbo_User> users) {
		this.users = users;
	}
	public Satisfaction() {
		super();
	}
	public Satisfaction(Long id, String name, Date satisfaction_Date, List<Dbo_User> users,
			List<Question_Satisfaction> questions) {
		super();
		this.id = id;
		this.name = name;
		Satisfaction_Date = satisfaction_Date;
		this.users = users;
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Satisfaction [id=" + id + ", name=" + name + ", Satisfaction_Date=" + Satisfaction_Date + ", users="
				+ users + ", questions=" + questions + "]";
	}
	

	
   

}

package Esprit.PiDev.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @OneToOne
	private Dbo_User user;
    
    
	@ManyToMany
	private List<Question_Satisfaction> questions;
	
	
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
	public Dbo_User getUser() {
		return user;
	}
	public void setUser(Dbo_User user) {
		this.user = user;
	}
	public List<Question_Satisfaction> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question_Satisfaction> questions) {
		this.questions = questions;
	}
	public Satisfaction(Long id, String name, Date satisfaction_Date, Dbo_User user,
			List<Question_Satisfaction> questions) {
		super();
		this.id = id;
		this.name = name;
		Satisfaction_Date = satisfaction_Date;
		this.user = user;
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Satisfaction [id=" + id + ", name=" + name + ", Satisfaction_Date=" + Satisfaction_Date + ", user="
				+ user + ", questions=" + questions + "]";
	}
	public Satisfaction() {
		super();
	}
	
	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/

	
   

}

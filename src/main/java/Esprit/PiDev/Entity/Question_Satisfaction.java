package Esprit.PiDev.Entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_Question_Satisfaction")
public class Question_Satisfaction {
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Question_id")
	private Long id;
	@Column(name="question")
	private String question_Sat;
	@Temporal(TemporalType.DATE)
	private  Date dateOfCreation;
	@ManyToMany
	private List<Satisfaction> satisfactions;
	@OneToMany(mappedBy="question")
	@JsonIgnore
	private Set<Answer_Satisfaction> answers;


	public Set<Answer_Satisfaction> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<Answer_Satisfaction> answers) {
		this.answers = answers;
	}
	public Question_Satisfaction() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestion_Sat() {
		return question_Sat;
	}
	public void setQuestion_Sat(String question_Sat) {
		this.question_Sat = question_Sat;
	}
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	public List<Satisfaction> getSatisfactions() {
		return satisfactions;
	}
	public void setSatisfactions(List<Satisfaction> satisfactions) {
		this.satisfactions = satisfactions;
	}
	public Question_Satisfaction(Long id, String question_Sat, Date dateOfCreation, List<Satisfaction> satisfactions,
			Set<Answer_Satisfaction> answers) {
		super();
		this.id = id;
		this.question_Sat = question_Sat;
		this.dateOfCreation = dateOfCreation;
		this.satisfactions = satisfactions;
		this.answers = answers;
	}

	






	
}

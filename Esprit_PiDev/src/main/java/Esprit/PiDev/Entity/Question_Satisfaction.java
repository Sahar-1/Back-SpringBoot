package Esprit.PiDev.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Enumerated(EnumType.STRING)
	@Column(name = "reviews")
	private Review reviews;
	private String role;
    @ManyToOne
	private Dbo_User user;
	
    
	@ManyToMany
	private List<Satisfaction> satisfactions;
	
	
	
	
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Satisfaction> getSatisfactions() {
		return satisfactions;
	}
	public void setSatisfactions(List<Satisfaction> satisfactions) {
		this.satisfactions = satisfactions;
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
	public Review getReviews() {
		return reviews;
	}
	public void setReviews(Review reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		return "Question_Satisfaction [id=" + id + ", question_Sat=" + question_Sat + ", dateOfCreation="
				+ dateOfCreation + ", reviews=" + reviews + "]";
	}
	public Question_Satisfaction(Long id, String question_Sat, Date dateOfCreation, Review reviews) {
		super();
		this.id = id;
		this.question_Sat = question_Sat;
		this.dateOfCreation = dateOfCreation;
		this.reviews = reviews;
	}
	public Question_Satisfaction() {
		super();
	}
	
	
	
}

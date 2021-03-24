package Esprit.PiDev.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
public class Answer_Satisfaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@Column(name = "reviews", length = 20)
	private Review review;
	@ManyToOne
	private Question_Satisfaction question;
	
	@ManyToOne
	private Dbo_User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public Question_Satisfaction getQuestion() {
		return question;
	}
	public void setQuestion(Question_Satisfaction question) {
		this.question = question;
	}
	public Dbo_User getUser() {
		return user;
	}
	public void setUser(Dbo_User user) {
		this.user = user;
	}
	public Answer_Satisfaction(Long id, Review review, Question_Satisfaction question, Dbo_User user) {
		super();
		this.id = id;
		this.review = review;
		this.question = question;
		this.user = user;
	}
	public Answer_Satisfaction() {
		super();
	}
	@Override
	public String toString() {
		return "Answer_Satisfaction [id=" + id + ", review=" + review + ", question=" + question + ", user=" + user
				+ "]";
	}

}

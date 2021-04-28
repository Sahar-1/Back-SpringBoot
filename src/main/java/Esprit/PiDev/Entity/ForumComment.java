package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "T_Forum_Comment")
public class ForumComment implements Serializable{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	/*-----------------------****Bean_Attributes****-------------------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="varchar(1000)",name="Answer_Forum")
	private String answer;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern ="yyyy-MM-dd" ,shape =Shape.STRING)
	private Date postedDate;
	
	
	/*-------------------------------association forumComment and ForumSubject--------------------------------------------------*/
	@ManyToOne
	@JsonIgnore
	ForumSubject forumSubject;
	
	/*-------------------------------association forumComment and user--------------------------------------------------*/

	@ManyToOne
	@JsonIgnore
	Dbo_User user;
	
	/*-----------------------****Constructors_Object****-------------------------------------*/
	

	
	


	public ForumComment(long id, String answer, Date postedDate, ForumSubject forumSubject, Dbo_User user) {
		super();
		this.id = id;
		this.answer = answer;
		this.postedDate = postedDate;
		this.forumSubject = forumSubject;
		this.user = user;
	}


	public ForumComment(long id, String answer, Date postedDate) {
		super();
		this.id = id;
		this.answer = answer;
		this.postedDate = postedDate;
	}


	public ForumComment() {
		super();
	}
	


	


	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/


	public long getId() {
		return id;
	}

	
	public void setId(long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public ForumSubject getForumSubject() {
		return forumSubject;
	}


	public void setForumSubject(ForumSubject forumSubject) {
		this.forumSubject = forumSubject;
	}


	public Dbo_User getUser() {
		return user;
	}


	public void setUser(Dbo_User user) {
		this.user = user;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}


	


	@Override
	public String toString() {
		return "ForumComment [id=" + id + ", answer=" + answer + ", postedDate=" + postedDate + "]";
	}




	

	



	


	
	

	
	
	

}

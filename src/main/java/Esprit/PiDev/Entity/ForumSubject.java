package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property ="id")
@Table(name = "T_Forum_Subject")
public class ForumSubject implements Serializable{
	
	/**
	 * 
	 */

  private static final long serialVersionUID = 1L;
	
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Title_forum")
	private   String title;
	
	@Column(columnDefinition="varchar(1000)",name="Question_Forum")
	private String question;
	
	@Temporal(TemporalType.DATE)
	private Date postedDate;
	private float status;
	
	/*-------------------------------association ForumSubject and forumComment  --------------------------------------------------*/

	@OneToMany(mappedBy="forumSubject"/*,fetch= FetchType.EAGER*/)
	List<ForumComment> forumComments;

	@OneToMany(mappedBy="forumSubject"/*,fetch= FetchType.EAGER*/)
	Set<Dbo_User> users;

	@OneToMany(mappedBy="forumSubject")
	List<Garden> gardens;



	

/*-----------------------****Constructors_Object****-------------------------------------*/

	
	

	


	


	public List<ForumComment> getForumComments() {
		return forumComments;
	}


	public void setForumComments(List<ForumComment> forumComments) {
		this.forumComments = forumComments;
	}


	public Set<Dbo_User> getUsers() {
		return users;
	}


	public void setUsers(Set<Dbo_User> users) {
		this.users = users;
	}




	

	public List<Garden> getGardens() {
		return gardens;
	}


	public void setGardens(List<Garden> gardens) {
		this.gardens = gardens;
	}


	public ForumSubject(long id, String title, String question, Date postedDate, List<ForumComment> forumsComment,
			List<Dbo_User> users, List<Garden> gardens) {
		super();
		this.id = id;
		this.title = title;
		this.question = question;
		this.postedDate = postedDate;
		}


	public ForumSubject() {
	super();
}




	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/

	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	

	public void setTitle(String title) {
		this.title = title;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public Date getPostedDate() {
		return postedDate;
	}


	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}


	public float getStatus() {
		return status;
	}


	public void setStatus(float status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ForumSubject [id=" + id + ", title=" + title + ", question=" + question + ", postedDate=" + postedDate
				+ ", status=" + status + "]";
	}


	/*@Override
	public String toString() {
		return "ForumSubject [id=" + id + ", title=" + title + ", question=" + question + ", postedDate=" + postedDate
				+ ", status=" + status + ", forumComments=" + forumComments + ", users=" + users + ", gardens="
				+ gardens + "]";
	}*/
	


	


	




	
	
	
	

	
	 
		
	
	   
}
package Esprit.PiDev.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_Forum")
public class Forum implements Serializable{
	
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@EmbeddedId
	private ForumPK forum_pk;
	
	 
	@Column(name="forumName")
	private   String forumName;
	
	 
	
	@Column(name="question")
	private String question;
	
	@Column(columnDefinition="varchar(1000)",name="discription")
	private String discription;
	
	@Temporal(TemporalType.DATE)
	private Date postedDate;
	
	@Column(name="answer")
	private String answer;

	
/*-------------------------------association Porteuse de données --------------------------------------------------*/
	@ManyToOne 
	@JoinColumn(name="idGarden",  insertable=false, updatable=false)
	private Garden  garden;
	
	@ManyToOne
	@JoinColumn(name="idUser",   insertable=false, updatable=false)
	private Dbo_User user;
	
	
	
/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/
	

	public String getForumName() {
		return forumName;
	}
	public Garden getGarden() {
		return garden;
	}
	public void setGarden(Garden garden) {
		this.garden = garden;
	}
	public Dbo_User getUser() {
		return user;
	}
	public void setUser(Dbo_User user) {
		this.user = user;
	}
	public ForumPK getForum_pk() {
		return forum_pk;
	}
	public void setForum_pk(ForumPK forum_pk) {
		this.forum_pk = forum_pk;
	}
	public void setForumName(String forumName) {
		this.forumName = forumName;
	}
 
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Date getPostedDate() {
		return postedDate;
	}
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/*-----------------------****Constructors_Object****-------------------------------------*/
	
	public Forum() {
		super();
	}
	
	
	
	
	 
		
	
	   
}
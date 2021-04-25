package Esprit.PiDev.Entity;

import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "T_Post")
public class Post {
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Post_id")
	private Long id;
	@Column(name = "Title", columnDefinition = "TEXT")
	private String title;
	@Column(name = "signaler")
	private Integer signaler;
	@Temporal(TemporalType.DATE)
	private Date dateOfCreation;
	@Column(name = "subject")
	private String subject;
	@Enumerated(EnumType.STRING)
	@Column(name = "types")
	private Type types;
	@JsonIgnore
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Reaction> reactions;
	@JsonIgnore
	@ManyToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
	private List<Dbo_User> user;
	@JsonIgnore
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Comment> comments;

	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Type getTypes() {
		return types;
	}

	public void setTypes(Type types) {
		this.types = types;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public Integer getSignaler() {
		return signaler;
	}

	public void setSignaler(Integer signaler) {
		this.signaler = signaler;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	public List<Dbo_User> getUser() {
		return user;
	}

	public void setUser(List<Dbo_User> user) {
		this.user = user;
	}

	/*-----------------------****Constructors_Object****-------------------------------------*/

	public Post() {
		super();
	}

	public Post(Long id, String title, Integer signaler, Date dateOfCreation, String subject, Type types,
			List<Reaction> reactions, List<Dbo_User> user, List<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.signaler = signaler;
		this.dateOfCreation = dateOfCreation;
		this.subject = subject;
		this.types = types;
		this.reactions = reactions;
		this.user = user;
		this.comments = comments;
	}

	/*-----------------------****TO_String()****-------------------------------------*/

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", signaler=" + signaler + ", dateOfCreation=" + dateOfCreation
				+ ", subject=" + subject + ", types=" + types + ", reactions=" + reactions + ", user=" + user
				+ ", comments=" + comments + "]";
	}

}

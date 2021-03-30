package Esprit.PiDev.Entity;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;

@Entity
@Table(name = "T_Comment")
public class Comment {
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@Id
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Comment_id")
	private Long id;
    @Column(name = "text")
    private String text;
    @JsonIgnore
	@ManyToOne
	private Dbo_User user;
    @JsonIgnore
	@ManyToOne
	private Post post;
	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Dbo_User getUser() {
		return user;
	}
	public void setUser(Dbo_User user) {
		this.user = user;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	/*-----------------------****Constructors_Object****-------------------------------------*/
	public Comment() {
		super();
	}
	
	public Comment(Long id, String text, Dbo_User user, Post post) {
		super();
		this.id = id;
		this.text = text;
		this.user = user;
		this.post = post;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", text=" + text + ", user=" + user + ", post=" + post + "]";
	}
	
	/*-----------------------****TO_String()****-------------------------------------*/




	

}

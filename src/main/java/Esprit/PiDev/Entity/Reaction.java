package Esprit.PiDev.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_Reaction")
public class Reaction {
	/*-----------------------****Bean_Attributes****-------------------------------------*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Reaction_id")
	private Long id;
    @Column(name="liked")
	private boolean liked;
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
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public Dbo_User getUser() {
		return user;
	}
	public void setUser(Dbo_User user) {
		this.user = user;
	}
	
	/*-----------------------****Constructors_Object****-------------------------------------*/


	public Reaction() {
		super();
	}
	 
	public Reaction(Long id, Boolean liked, Dbo_User user, Post post) {
		super();
		this.id = id;
		this.liked = liked;
		this.user = user;
		this.post = post;
	}
	@Override
	public String toString() {
		return "Reaction [id=" + id + ", liked=" + liked + ", user=" + user + ", post=" + post + "]";
	}
	
	/*-----------------------****TO_String()****-------------------------------------*/


	
	
	
	
	
}

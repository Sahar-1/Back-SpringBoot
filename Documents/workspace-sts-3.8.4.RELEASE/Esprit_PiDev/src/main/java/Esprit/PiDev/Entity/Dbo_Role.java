package Esprit.PiDev.Entity;

 
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "T_User_Role")
public class Dbo_Role   {

	 

	/*-----------------------****Bean_Attributes****-------------------------------------*/

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Role_id")
	private Long id;

	public Dbo_Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Dbo_Role() {
		super();
	}

	public Dbo_Role(String name) {
		super();
		this.name = name;
	}

	@NotNull
	@Column(name = "User_Role_Name")
	private String name;
	
	@OneToMany( targetEntity=Dbo_User.class ,
				mappedBy="role" ,
				fetch = FetchType.LAZY ,
				cascade =CascadeType.ALL
				)
	private Set<Dbo_User> users;

	/*-----------------------****Getters_Setters_Methods()****-------------------------------------*/

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

}

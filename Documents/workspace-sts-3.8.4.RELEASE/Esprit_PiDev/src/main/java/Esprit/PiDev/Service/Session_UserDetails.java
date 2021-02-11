package Esprit.PiDev.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Esprit.PiDev.Entity.Dbo_User;
 
public class Session_UserDetails implements UserDetails   {
	/**
	 * 
	 */
	private Long id;

	 
	private String username;

	private String email;

	@JsonIgnore
	private String password;
	private static final long serialVersionUID = 1L;
 
	
	 

	 
	private Collection<? extends GrantedAuthority> authorities;
	
 

	/**
	 * @return the authorities
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}



	public Session_UserDetails(Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities=authorities;
	}

 

	public static Session_UserDetails build(Dbo_User userNow) {
		List<GrantedAuthority> authorities = userNow.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
				 

		return new Session_UserDetails(
				userNow.getId(), 
				userNow.getFirstName(), 
				userNow.getEmail(),
				userNow.getPassword(), 
				authorities);
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

 

	public void setUsername(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

 



	

}

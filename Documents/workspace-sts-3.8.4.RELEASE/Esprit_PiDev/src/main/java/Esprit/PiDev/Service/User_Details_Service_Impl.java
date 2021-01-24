package Esprit.PiDev.Service;

import java.util.ArrayList;
import java.util.Collection;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class User_Details_Service_Impl implements UserDetailsService {

	@Autowired
	private User_Repository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Dbo_User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Email " + email + " Not found");
		}
		return new User(user.getEmail(), user.getPassword(), getAuthorities(user));
	}

 

	private Collection<GrantedAuthority> getAuthorities(Dbo_User user) {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (user.getRole().getName().equalsIgnoreCase("admin")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;

	}
}

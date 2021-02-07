package Esprit.PiDev.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Repository.User_Repository;
 
@Service
public class User_Details_Service_Impl implements UserDetailsService {

	@Autowired
	private User_Repository userRepository;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Dbo_User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user with this Email " + email  );
		}
		return   Session_UserDetails.build(user);
	}
	
	

 
	
}

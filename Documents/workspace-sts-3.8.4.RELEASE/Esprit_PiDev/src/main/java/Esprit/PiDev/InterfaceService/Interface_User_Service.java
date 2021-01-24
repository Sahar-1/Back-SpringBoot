package Esprit.PiDev.InterfaceService;

import java.util.List;
import java.util.Optional;

import Esprit.PiDev.Entity.Dbo_User;
 
public interface Interface_User_Service {
	
	  Dbo_User addUser(Dbo_User userToAdd);
	
	  List<Dbo_User> retrieveAllUsers();

	  Dbo_User saveOrUpdate(Dbo_User user);

	  Optional<Dbo_User> findById(Long id);

	  String deleteById(Long id);
	
	   
	 
}

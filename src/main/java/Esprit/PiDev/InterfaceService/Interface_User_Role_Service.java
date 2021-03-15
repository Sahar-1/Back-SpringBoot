package Esprit.PiDev.InterfaceService;

import java.util.Collection;
import java.util.Optional;

import Esprit.PiDev.Entity.Dbo_Role;
 
public interface Interface_User_Role_Service {

	 Dbo_Role addRole(Dbo_Role roleToAdd);
		
	 Collection<Dbo_Role> retrieveAllRole();

	 Dbo_Role saveOrUpdate(Dbo_Role role);

	 Optional<Dbo_Role> findById(Long id);

	 String deleteById(Long id);
}

package Esprit.PiDev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Dbo_Role;
 
@Repository
public interface Role_Repository extends JpaRepository<Dbo_Role, Long>{

	
	   Dbo_Role findByName( String name);
}

package Esprit.PiDev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Dbo_User;
@Repository
public interface User_Repository extends JpaRepository<Dbo_User, Long> {
	
	@Query ("FROM Dbo_User WHERE email=:email")
	Dbo_User findByEmail (@Param("email") String email);

	 
	
}

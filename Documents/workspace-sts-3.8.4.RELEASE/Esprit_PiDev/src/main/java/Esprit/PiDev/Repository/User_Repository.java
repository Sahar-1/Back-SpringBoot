package Esprit.PiDev.Repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import Esprit.PiDev.Entity.Dbo_User;
 
@Repository
public interface User_Repository extends JpaRepository<Dbo_User, Long> {

	@Query("FROM Dbo_User WHERE email=:email")
	Dbo_User findByEmail(@Param("email") String email);
	
	@Query("select u.id FROM Dbo_User as u WHERE u.email = :Email")
	Long findIDByEmail(@Param("Email") String Email);

	Optional<Dbo_User> findByEmailIgnoreCase(String email);

	Optional<Dbo_User> findByFirstNameIgnoreCase(String firstName);

	Optional<Dbo_User> findByLastNameIgnoreCase(String lastName);
	
	@Transactional
	@Modifying
	@Query("update Dbo_User u set u.lastLoggedOut =:time  where u.id =:id ")
	int updateUserTimeLoggedOut( @Param("id") Long id, @Param("time") Date time);
	
	@Transactional
	@Modifying
	@Query("update Dbo_User u set u.lastLoggedIn =:time  where u.id =:id ")
	int updateUserTimeLoggedIn( @Param("id") Long id, @Param("time")  Date time);
	
	@Transactional
	@Modifying
	@Query("update Dbo_User u set u.Session_Id =:Session_Id  where u.id =:id ")
	int updateUserSessionId( @Param("Session_Id") String Session_Id , @Param("id")  Long id);
	
	@Query("select u.Session_Id FROM Dbo_User as u WHERE u.id = :id ")
	String getOldSessionId(@Param("id") Long id);

	@Query("select u.id FROM Dbo_User as u WHERE u.email = :email ")
	Long FindIDUserByEmail(@Param("email") String email);
	
	 
	@Query(value = "SELECT u FROM Dbo_User u where u.email = ?1 and u.password = ?2 ")
    Dbo_User login(String email,String password);
    
	Boolean existsByFirstName(String firstName);

	Boolean existsByEmail(String email);
	
}

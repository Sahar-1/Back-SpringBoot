package Esprit.PiDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Claim;
import Esprit.PiDev.Entity.Dbo_User;

@Repository
public interface Claim_Repository extends CrudRepository<Claim, Long> {

	@Query("SELECT r " + "FROM Claim r " + "WHERE r.id IN (" + "   SELECT MAX(l.id) " + "   FROM Claim l "
			+ "   WHERE l.user = :user OR l.recipient = :user " + "   GROUP BY " + "       CASE "
			+ "           WHEN l.recipient = :user THEN l.user "
			+ "           WHEN l.user = :user THEN l.recipient " + "           ELSE :user " + "       END) "
			+ "ORDER BY r.Date DESC")
	List<Claim> findLastReclamationsByUser(@Param("user") Dbo_User user);
	
	
	@Query("SELECT r FROM Claim r WHERE r.user.firstName LIKE CONCAT('%',:string,'%')")
	public List<Claim> searchClaim(@Param("string") String msg);
	
}
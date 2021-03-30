package Esprit.PiDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.Message;


@Repository
public interface Message_Repository extends JpaRepository<Message, Long>{


	@Query("select m  from Message m  where ( m.sender=:user1 and m.receiver=:user2) or ( m.sender=:user1 and m.receiver=:user2)   ORDER by m.date DESC ")
	 public List<Message> conversations(@Param("user1") Dbo_User user1,@Param("user2") Dbo_User user2);

	@Query("SELECT m FROM Message m WHERE r=m.sender.username LIKE CONCAT('%',:username,'%')")
	public List<Message> searchMessages(@Param("username") String username);
}

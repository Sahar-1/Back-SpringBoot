package Esprit.PiDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Message;


@Repository
public interface Message_Repository extends CrudRepository<Message, Long>{
	@Query("select u from Message u where u.receiver=:receiver and u.sender=:sender")
    List<Message> Conversations(@Param("sender")Long sender,@Param("receiver")Long receiver);
}

package Esprit.PiDev.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Message;


@Repository
public interface Message_Repository extends CrudRepository<Message, Long>{
	/*@Query("select u from Message u where u.receiver=:userId")
    public List<Message> FindMyMessages(@Param("userId")Long userId);*/
}

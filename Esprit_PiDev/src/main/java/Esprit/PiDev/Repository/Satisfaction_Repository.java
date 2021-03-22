package Esprit.PiDev.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Satisfaction;


@Repository
public interface Satisfaction_Repository extends CrudRepository<Satisfaction, Long> {
 
	
	Satisfaction findByUser(Long id );
	

}

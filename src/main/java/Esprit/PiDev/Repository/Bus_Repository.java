package Esprit.PiDev.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Bus;

@Repository
public interface Bus_Repository extends CrudRepository<Bus,Integer> {

	
	
	Bus findByName(String name);
	
	

}

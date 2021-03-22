package Esprit.PiDev.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Contract;
@Repository
public interface Contract_Repository extends CrudRepository<Contract, Long>{

	
}

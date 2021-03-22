package Esprit.PiDev.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Bill;
@Repository
public interface Bill_Repository extends CrudRepository<Bill, Long>{

}

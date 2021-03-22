package Esprit.PiDev.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Plan;

@Repository
public interface Plan_Repository extends CrudRepository<Plan, Long> {


}
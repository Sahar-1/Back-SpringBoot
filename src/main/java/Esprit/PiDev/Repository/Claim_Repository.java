package Esprit.PiDev.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Claim;

@Repository
public interface Claim_Repository extends CrudRepository<Claim, Long> {


}
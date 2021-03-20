package Esprit.PiDev.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Historique;

@Repository
public interface Historique_Repository extends CrudRepository<Historique, Integer> {

}

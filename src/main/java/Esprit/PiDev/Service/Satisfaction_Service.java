package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Satisfaction;

public interface Satisfaction_Service {
	
	public ResponseEntity<?> addSatisfaction(Satisfaction satisfaction, Long iduser);

	List<Satisfaction> retrieveAllSatisfactions();

	public ResponseEntity<?>  saveOrUpdate(Satisfaction satisfaction,Long iduser,Long sat);

	Satisfaction findSatisfactionById(Long id);

	public ResponseEntity<?>  deleteSatisfactionById(Long id);
	
	Satisfaction retrieveSatisfaction(Satisfaction satisfaction);

	public ResponseEntity<?>  deleteSatisfaction(Satisfaction satisfaction);
}

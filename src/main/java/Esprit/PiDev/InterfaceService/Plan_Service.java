package Esprit.PiDev.InterfaceService;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Plan;

public interface Plan_Service {
	public ResponseEntity<?>  Add_Plan(long user_id,Plan P);
	public ResponseEntity<?> getAllPlan(long user_id);
}

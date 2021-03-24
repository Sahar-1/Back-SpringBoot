package Esprit.PiDev.InterfaceService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Claim;
import Esprit.PiDev.Entity.Dbo_User;

public interface Claim_Service {

	public ResponseEntity<?>  Add_Claim(long user_id,Claim C);
	public ResponseEntity<?> Delete_Claim(long user_id, int Claim_id);
	//void Delete_Claim_Id(Long id);
	
	public ResponseEntity<?> Retrieve_All_Claims(long user_id,Long id_garden);
	
	public ResponseEntity<?>  affectation_Claim_2 (long user_id, Claim claim,Long garden_id);
	public ResponseEntity<?> Validate_Claim(long user_id, int claim_id,Claim claim);
	public ResponseEntity<?>investisment (long user_id, Long id_garden);

	public List<Claim> getLastReclamations(long user_id);
	
	public List<Claim> searchclaim(String msg);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

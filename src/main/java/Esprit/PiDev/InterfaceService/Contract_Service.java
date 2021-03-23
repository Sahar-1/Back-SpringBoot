package Esprit.PiDev.InterfaceService;

import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Contract;

public interface Contract_Service {
	public ResponseEntity<?>  Add_Contract(long user_id,Contract C ,Long idgard);
	public ResponseEntity<?> Delete_Contract(long user_id, int Contract_id);
	public ResponseEntity<?> getAllContracts(long user_id);
	public ResponseEntity<?> UpdateContract(long user_id, Long Contract_id,Contract contract);
	/*Contract Update_Contract(Contract C);
	List<Contract> Retrieve_All_Contracts();
	Contract Retrieve_Contract(Long id);
	
	*/
	Contract Retrieve_Contract(Long id);
	public void promo(Contract cont);
}

	
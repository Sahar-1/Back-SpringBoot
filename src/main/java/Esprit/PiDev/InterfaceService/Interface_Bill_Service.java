package Esprit.PiDev.InterfaceService;


import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Bill;

public interface Interface_Bill_Service {


	ResponseEntity<?> Add_Bill(Bill C ,Long iduser);
   // ResponseEntity<?>  Delete_Bill(Long idBill,Long user_id);
   // ResponseEntity<?> Update_Bill(Bill C,Long user_id);
	//ResponseEntity<?> Retrieve_Bill(Long id,Long user_id);
	//ResponseEntity<?> Retrieve_All_Bill(Long user_id);
	
	
	
	
}


package Esprit.PiDev.InterfaceService;


import org.springframework.http.ResponseEntity;

import Esprit.PiDev.Entity.Bill;

public interface Interface_Bill_Service {


	ResponseEntity<?> Add_Bill(Long iduser_connect,Bill C , Long iduser, Long idgarden);
   // ResponseEntity<?>  Delete_Bill(Long idBill,Long user_id);
    ResponseEntity<?> Update_Bill(Bill C,Long bill_id,Long user_id);
	//ResponseEntity<?> Retrieve_Bill(Long id,Long user_id);
	//ResponseEntity<?> Retrieve_All_Bill(Long user_id);
	public void discountEnfant(long id_parent,Long garden);
	public int countenfantpargarden(long id_parent, Long garden);
	
	
	
	
}

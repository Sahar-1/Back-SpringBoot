package Esprit.PiDev.InterfaceService;


import java.util.List;

import Esprit.PiDev.Entity.Bill;
import Esprit.PiDev.dto.BillDto;

public interface Interface_Bill_Service {


	public void Add_Bill(Bill C, Long iduser, Long idgarden);
   // ResponseEntity<?>  Delete_Bill(Long idBill,Long user_id);
    void Update_Bill(Bill C,Long bill_id);
    public void deleteBill(Long id);
   // public ResponseEntity<?> retrieveBill(long user_id, Long id);
	public List<Bill>  Retrieve_All_bills();
	public List<BillDto> findAllBills();
	public Bill findBill(Long id);
	public void discountEnfant(long id_parent,Long garden);
	public int countenfantpargarden(long id_parent, Long garden);
	
	
	
	
}

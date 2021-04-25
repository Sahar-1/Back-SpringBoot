
package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Bill;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Interface_Bill_Service;
import Esprit.PiDev.Repository.Bill_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Bill_Service_Impl implements Interface_Bill_Service {

	@Autowired
	Bill_Repository Bill_rep;
	@Autowired
	User_Repository ur1;
	@Autowired
	User_Role_Service ur;
	@Autowired
	Garden_Repository gard_rep;
	@Autowired
	Garden_Service grdserv;
	
	
	// the admin can add a bill
	
	@Override
	public ResponseEntity<?> Add_Bill(Long user_connect,Bill C, Long iduser, Long idgarden) {
Garden garden =gard_rep.findById(idgarden).orElseThrow(()-> new RuntimeException("null identify"));
		Dbo_User dbo_User = ur1.findById(iduser).orElseThrow( ()-> new RuntimeException("null identify"));
		Dbo_User dbo_User_connect = ur1.findById(user_connect).orElseThrow( ()-> new RuntimeException("null identify"));

		if (dbo_User_connect.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			System.out.println(dbo_User.toString());
			C.setGarden(garden);
  			C.setUser(dbo_User);
			Bill_rep.save(C);
			return ResponseEntity.ok(new MessageResponse("bill is saved"));

		}

		else {

			return ResponseEntity.ok(new MessageResponse("user isn't admin"));

		}

	}
	
	@Override
	public ResponseEntity<?> Update_Bill(Bill C,Long bill_id, Long user_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			 Bill bill_To_Update =Bill_rep.findById((long) bill_id).orElse(null);
			if ( bill_To_Update != null) {
				
				bill_To_Update.setTitle(C.getTitle());
				bill_To_Update.setDateStart(C.getDateStart());
				bill_To_Update.setDateDeadline(C.getDateDeadline());
				bill_To_Update.setDiscription(C.getDiscription());
				bill_To_Update.setTotal(C.getTotal());
				bill_To_Update.setDiscount(C.getDiscount());
				bill_To_Update.setUser(C.getUser());
				bill_To_Update.setamount(C.getamount());
				bill_To_Update.setGarden(C.getGarden());
				
			
				Bill_rep.save(bill_To_Update);
				return ResponseEntity.ok(new MessageResponse("bill is modified "));
			} else {
				return ResponseEntity.ok(new MessageResponse("bill isn't modified"));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user isn't admin"));

		}


	}
	
	
	
	public int countenfantpargarden(long id_parent, Long garden) {
		List<Dbo_User> list = null ;
		Dbo_User dbo_User = ur1.findById(id_parent).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {

			list =ur1.afficher_enfant_byParent(id_parent, garden);
			int	i=0;
			for (Dbo_User dbo_User2 : list) {
				dbo_User2.getId();
				i++;
			}
			return i;
			//System.out.println("les nombres des enfants    "  + i  );
		}
	

		else
		{
			return -1;

		}

	
		
		

		
	}

	
	


	
	@Override
	public void discountEnfant(long id_parent,Long garden)
	{
		Dbo_User parantremise=ur1.findById(id_parent).get();
		int dis=countenfantpargarden(id_parent, garden);
		if(dis  == 2)
		{
			
			
if(parantremise != null)
{
	//List<Bill> bills=new ArrayList<Bill>();
/*
	for (Bill bill : parantremise.getBills()) {
		bills.add(bill);
		break;
	}
	*/
	
	
	//Bill bil =Bill_rep.bill_byparent(id_parent);
	//Garden garden2 =Bill_rep.bill_bygarden(bil.getGarden().getId());
			
	
		//System.out.println(garden2.toString());
		//System.out.println(bil.toString());
	Bill bil =Bill_rep.bill_byparent(id_parent,garden);
	
	//System.out.println(bill.toString());
	
		bil.setDiscount(20);
		bil.setTotal(100-bil.getDiscount());
		Bill_rep.save(bil);
		//Update_Bill(bil, bil.getId(), id_parent);
	//}
		
		
}
			
			
		}
		else if(dis  == 3)
		{
			if(parantremise != null)
			{
				//List<Bill> bills=new ArrayList<Bill>();
			/*
				for (Bill bill : parantremise.getBills()) {
					bills.add(bill);
					break;
				}
				*/
				
				
				//Bill bil =Bill_rep.bill_byparent(id_parent);
				//Garden garden2 =Bill_rep.bill_bygarden(bil.getGarden().getId());
						
				
					//System.out.println(garden2.toString());
					//System.out.println(bil.toString());
				Bill bil =Bill_rep.bill_byparent(id_parent,garden);
				
				//System.out.println(bill.toString());
				
					bil.setDiscount(30);
					bil.setTotal(100-bil.getDiscount());
					Bill_rep.save(bil);
					//Update_Bill(bil, bil.getId(), id_parent);
				//}
			
		}
		}
		else
		{
			Bill bil =Bill_rep.bill_byparent(id_parent,garden);
			
			
				bil.setDiscount(0);
				bil.setTotal(100);
				Bill_rep.save(bil);
		}
	}
		
		
		
		
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
	@Override
	public ResponseEntity<?> Delete_Bill(Long idBill, Long user_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			gard_rep.deleteById(idBill);
			return ResponseEntity.ok(new MessageResponse("bill deleted "));
		}

		else {

			return ResponseEntity.ok(new MessageResponse("this user isn't admin"));

		}

	}
	*/
	
	//the admin can update a bill
	/*

	@Override
	public ResponseEntity<?> Update_Bill(Bill C, Long user_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {

			;
			return ResponseEntity.ok(new MessageResponse("bill is saved"));

		}

		else {

			return ResponseEntity.ok(new MessageResponse("user isn't admin"));

		}

	}

	@Override
	public ResponseEntity<?> Retrieve_Bill(Long id, Long user_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

		{

			return ResponseEntity.ok(new MessageResponse("" + Bill_rep.findById(id).get()));
		}
		{
			return ResponseEntity.ok(new MessageResponse("user isn't admin"));

		}
	}
/*
	@Override
	public ResponseEntity<?> Retrieve_All_Bill(Long user_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

		{
			return ResponseEntity.ok(new MessageResponse("" + Bill_rep.findAll()));

		}
		{
			return ResponseEntity.ok(new MessageResponse("user isn't admin"));

		}

	}
	*/



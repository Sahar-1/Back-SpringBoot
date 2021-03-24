package Esprit.PiDev.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Bill;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
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

	
	
	// the admin can add a bill
	
	@Override
	public ResponseEntity<?> Add_Bill(Bill C, Long iduser) {

		Dbo_User dbo_User = ur1.findById(iduser).orElseThrow( ()-> new RuntimeException("null identify"));
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
  			C.setUser(dbo_User);
			Bill_rep.save(C);
			return ResponseEntity.ok(new MessageResponse("bill is saved"));

		}

		else {

			return ResponseEntity.ok(new MessageResponse("user isn't admin"));

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

}

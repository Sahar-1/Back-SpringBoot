package Esprit.PiDev.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import Esprit.PiDev.Entity.Bill;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.InterfaceService.Interface_Bill_Service;
import Esprit.PiDev.Repository.Bill_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.dto.BillDto;

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
	@Autowired
	TempUserService userService;
	

	@Override
	public List<Bill> Retrieve_All_bills() {
		Dbo_User dbo_User = userService.getConnectedUser();

		// Dbo_User dbo_User = ur1.findById( ).orElse(null);

		// if (dbo_User.getRole().stream().anyMatch(e ->
		// e.getName().equals(ERole.ROLE_ADMIN)))

		//// {
		List<Bill> list = (List<Bill>) Bill_rep.findAll();
		return list;

		// }
		// return ResponseEntity.ok(new MessageResponse("user n'est pas
		// admin"));
	}

	@Override
	public List<BillDto> findAllBills() {
		Dbo_User dbo_User = userService.getConnectedUser();

		List<Bill> bills = (List<Bill>) Bill_rep.findAll();
		ObjectMapper mapper = new ObjectMapper();
		return bills.stream().map(bill-> {
			BillDto dto = new BillDto();
			dto.setAmount(bill.getamount());
			dto.setDateDeadline(bill.getDateDeadline());
			dto.setDateStart(bill.getDateStart());
			dto.setDiscount(bill.getDiscount());
			dto.setTitle(bill.getTitle());
			dto.setDiscription(bill.getDiscription());
			dto.setTotal(bill.getTotal());
			dto.setId(bill.getId());
			
		dto.setUserId(bill.getUser().getId());
		dto.setUserFirstName(bill.getUser().getFirstName());
			dto.setUserLastName(bill.getUser().getLastName());
		dto.setGardenId(bill.getGarden().getId());
			dto.setGardenName(bill.getGarden().getName());
			return dto;
		}).collect(Collectors.toList());
	}

	/*
	 * @Override public ResponseEntity<?> retrieveBill( Long id) { //Dbo_User
	 * dbo_User = ur1.findById(user_id).orElse(null);
	 * 
	 * //if (dbo_User.getRole().stream().anyMatch(e ->
	 * e.getName().equals(ERole.ROLE_ADMIN)))
	 * 
	 * // { return ResponseEntity.ok(new MessageResponse("" +
	 * Bill_rep.findById(id).get()));
	 * 
	 * //} // { // return ResponseEntity.ok(new
	 * MessageResponse("user n'est pas admin"));
	 * 
	 * } }
	 */

	@Override
	public Bill findBill(Long id) {
		Dbo_User dbo_User = userService.getConnectedUser();


		return Bill_rep.findById(id).get();

	}

	@Override
	public void Add_Bill(Bill C, Long iduser, Long idgarden) {
		Dbo_User dbo_User = userService.getConnectedUser();

		Garden garden = gard_rep.findById(idgarden).orElseThrow(() -> new RuntimeException("null identify"));
		Dbo_User userBill = ur1.findById(iduser).orElseThrow(() -> new RuntimeException("null identify"));

		System.out.println(userBill.toString());
		C.setGarden(garden);
		C.setUser(userBill);
		Bill_rep.save(C);

	}

	@Override
	public void Update_Bill(Bill C, Long bill_id) {
		Dbo_User dbo_User = userService.getConnectedUser();

		// Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		// if (dbo_User.getRole().stream().anyMatch(e ->
		// e.getName().equals(ERole.ROLE_ADMIN))) {
		Bill bill_To_Update = Bill_rep.findById((long) bill_id).orElse(null);
		if (bill_To_Update != null) {

			bill_To_Update.setTitle(C.getTitle());
			bill_To_Update.setDateStart(C.getDateStart());
			bill_To_Update.setDateDeadline(C.getDateDeadline());
			bill_To_Update.setDiscription(C.getDiscription());
			bill_To_Update.setTotal(C.getTotal());
			bill_To_Update.setDiscount(C.getDiscount());
			bill_To_Update.setAmount(C.getamount());

			Bill_rep.save(bill_To_Update);
			// return ResponseEntity.ok(new MessageResponse("bill is modified
			// "));
			// } else {
			// return ResponseEntity.ok(new MessageResponse("bill isn't
			// modified"));
			// }
			// }

			// else {

			// return ResponseEntity.ok(new MessageResponse("user isn't
			// admin"));

		}

	}

	public int countenfantpargarden(long id_parent, Long garden) {
		List<Dbo_User> list = null;
		Dbo_User dbo_User = ur1.findById(id_parent).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {

			list = ur1.afficher_enfant_byParent(id_parent, garden);
			int i = 0;
			for (Dbo_User dbo_User2 : list) {
				dbo_User2.getId();
				i++;
			}
			return i;
			// System.out.println("les nombres des enfants " + i );
		}

		else {
			return -1;

		}

	}

	@Override
	public void deleteBill(Long id) {
		Dbo_User dbo_User = userService.getConnectedUser();

		Bill_rep.deleteById(id);
	}

	@Override
	public void discountEnfant(long id_parent, Long garden) {
		Dbo_User dbo_User = userService.getConnectedUser();

		Dbo_User parantremise = ur1.findById(id_parent).get();
		int dis = countenfantpargarden(id_parent, garden);
		if (dis == 2) {

			if (parantremise != null) {

				Bill bil = Bill_rep.bill_byparent(id_parent, garden);

				bil.setDiscount(20);
				bil.setTotal(100 - bil.getDiscount());
				Bill_rep.save(bil);
				// Update_Bill(bil, bil.getId(), id_parent);

			}

		} else if (dis == 3) {
			if (parantremise != null) {

				Bill bil = Bill_rep.bill_byparent(id_parent, garden);

				bil.setDiscount(30);
				bil.setTotal(100 - bil.getDiscount());
				Bill_rep.save(bil);

			}
		} else {
			Bill bil = Bill_rep.bill_byparent(id_parent, garden);

			bil.setDiscount(0);
			bil.setTotal(100);
			Bill_rep.save(bil);
		}
	}

}

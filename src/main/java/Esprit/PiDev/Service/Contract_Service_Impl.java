package Esprit.PiDev.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Contract;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.Entity.Plan;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Contract_Service;
import Esprit.PiDev.Repository.Contract_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.Plan_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Contract_Service_Impl  implements Contract_Service{
	@Autowired
	Contract_Repository cnt_rep;
	@Autowired
	Garden_Repository gard_rep;
	@Autowired
	Plan_Repository pln_rep;
	@Autowired
	User_Repository ur1;
	public ResponseEntity<?> Add_Contract(long user_id,Contract C, Long idgarden)
	{
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))
		{
		Garden gard=gard_rep.findById(idgarden).orElseThrow( ()-> new RuntimeException("null id"));
		C.setGarden(gard);
	cnt_rep.save(C);
		 return ResponseEntity.ok(new MessageResponse("success" +  C.getDiscription()));
	}
		else 
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

	}
	}

	@Override
	public ResponseEntity<?> Delete_Contract(long user_id, int Contract_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			 Contract contract =cnt_rep.findById((long) Contract_id).orElse(null);
			 if ( contract != null) {
				 cnt_rep.deleteById((long) Contract_id);
					return ResponseEntity.ok(new MessageResponse("Contract est supprimé "));
				} else {
					return ResponseEntity.ok(new MessageResponse("Contract n'existe pas"));
				}
			}

			else {

				return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

			}
	}

	@Override
	public ResponseEntity<?> getAllContracts(long user_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

		{
			return ResponseEntity.ok(new MessageResponse("" + cnt_rep.findAll()));

		}
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

		}
	}

	@Override
	public ResponseEntity<?> UpdateContract(long user_id,Long Contract_id, Contract contract) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			 Contract Contract_To_Update =cnt_rep.findById((long) Contract_id).orElse(null);
			if ( Contract_To_Update != null) {
				
				Contract_To_Update.setTitle(contract.getTitle());
				Contract_To_Update.setGarden(contract.getGarden());
				Contract_To_Update.setType(contract.getType());
				Contract_To_Update.setDate_Start(contract.getDate_Start());
				Contract_To_Update.setDate_deadline(contract.getDate_deadline());
				Contract_To_Update.setPrix(contract.getPrix());
				Contract_To_Update.setPaidOrNot(contract.isPaidOrNot());
				
				
				cnt_rep.save(Contract_To_Update);
				return ResponseEntity.ok(new MessageResponse("Contract est bien modifiée "));
			} else {
				return ResponseEntity.ok(new MessageResponse("Contract n'existe pas"));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}
	public Contract Retrieve_Contract(Long id) {
		// TODO Auto-generated method stub
		return cnt_rep.findById(id).get() ;
	}
	
	@Override
	public void promo(Contract cont)
	{

		float prix= (float) (cont.getPrix()-(cont.getPrix()* 0.15));
		 
		 cont.setPrix(prix );
		 cnt_rep.save(cont);
	}
	
	@Override
	public void affecterPlanAContrat(Long Contract_id, Long Plan_Id) {
	 
		Contract c =cnt_rep.findById(Contract_id).orElse(null);
		Plan p =pln_rep.findById(Plan_Id).orElse(null);
		p.setContract(c);
		pln_rep.save(p);
		
	}
}
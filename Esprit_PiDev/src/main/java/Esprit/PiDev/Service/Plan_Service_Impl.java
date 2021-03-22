package Esprit.PiDev.Service;

import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

 import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
 import Esprit.PiDev.Entity.Plan;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Plan_Service;
 import Esprit.PiDev.Repository.Plan_Repository;
import Esprit.PiDev.Repository.User_Repository;
@Service
public class Plan_Service_Impl implements Plan_Service{

	
	
	@Autowired
	Plan_Repository Plan_rep;
	
	@Autowired
	User_Repository ur1_rep;

	@Autowired
	User_Service us;
	@Autowired
	User_Role_Service ur;
	@Override
	public ResponseEntity<?> Add_Plan(long user_id, Plan plan) {
		Dbo_User dbo_User =  ur1_rep.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			
			Plan_rep.save(plan);
				return ResponseEntity.ok(new MessageResponse("plan est bien enregistrée"));

			//}

		}

		else {

			return ResponseEntity.ok(new MessageResponse("plan n'est pas un admin"));

		}
		
	}
	
	public ResponseEntity<?> getAllPlan(long user_id) {
		Dbo_User dbo_User = ur1_rep.findById(user_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

		{
			return ResponseEntity.ok(new MessageResponse("" + Plan_rep.findAll()));

		}
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

		}

	}
}

package Esprit.PiDev.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Bus;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Bus_Repository;
import Esprit.PiDev.Repository.Classe_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;



@Service
public class Bus_Service {
	@Autowired
	Garden_Repository garden_Repository;
	@Autowired
	User_Repository ur1;
	@Autowired
	User_Service us;
	@Autowired
	User_Role_Service ur;

	@Autowired
	Role_Repository role_Repository;

	@Autowired
	Interface_User_Service userService;

	@Autowired
	Classe_Repository classe_Repository;
	
	@Autowired
	Bus_Repository bus_Repository;

	
	//****************************************getAllBus_ByGarden******************************************************//

	public ResponseEntity<?> getAllBus_ByGarden(long user_id,Long garden_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		 Garden garden =garden_Repository.findById(garden_id).orElse(null);
		 List<Bus> bus = new ArrayList<>();
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

		{
			for (Bus bus2 : garden.getBus()) {
				
				bus.add(bus2);
			}
			
			
			return ResponseEntity.ok(new MessageResponse("" + bus ));

		}
		
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

		}

	}
	//****************************************getAllBus_ByGarden******************************************************//

	
	
	
	//****************************************ajouterBus_byGarden******************************************************//

	
	

	public ResponseEntity<?> ajouterBus_byGarden(long user_id, Bus bus,Long garden_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		 Garden garden =garden_Repository.findById(garden_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			Bus bus1 = bus_Repository.findByName(bus.getName());
			if (bus1 != null) {
				return ResponseEntity.ok(new MessageResponse("bus existe déja"));

			}

			else {
				bus.setGarden(garden);
				bus_Repository.save(bus);
				return ResponseEntity.ok(new MessageResponse("bus est bien enregistrée"));

			}

		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}
	//****************************************ajouterBus_byGarden******************************************************//


	
	//****************************************DeleteBus******************************************************//

	public ResponseEntity<?> DeleteBus(long user_id, int bus_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			 Bus bus =bus_Repository.findById(bus_id).orElse(null);
			if ( bus != null) {
				bus_Repository.deleteById(bus_id);
				return ResponseEntity.ok(new MessageResponse("bus est supprimé "));
			} else {
				return ResponseEntity.ok(new MessageResponse("bus n'existe pas"));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}
	//****************************************DeleteBus******************************************************//

	
	
	//****************************************UpdateBus******************************************************//

	public ResponseEntity<?> UpdateBus(long user_id, int bus_id,Bus bus) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			 Bus bus_To_Update =bus_Repository.findById(bus_id).orElse(null);
			if ( bus_To_Update != null) {
				
				bus_To_Update.setName(bus.getName());
				
				bus_Repository.save(bus_To_Update);
				return ResponseEntity.ok(new MessageResponse("bus est bien modifiée "));
			} else {
				return ResponseEntity.ok(new MessageResponse("bus n'existe pas"));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}
	//****************************************UpdateBus******************************************************//

	

}

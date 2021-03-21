package Esprit.PiDev.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Bus;
import Esprit.PiDev.Entity.Classe;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.Entity.Trajet;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Bus_Repository;
import Esprit.PiDev.Repository.Classe_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.Trajet_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Trajet_Service {
	
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
	@Autowired
	Trajet_Repository trajet_Repository;
	
	

	//****************************************getAllChauffeur_ByGarden******************************************************//

	public ResponseEntity<?> getAllChauffeur_ByGarden(long user_id,int garden_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		 Garden garden =garden_Repository.findById(garden_id).orElse(null);
		 List<Dbo_User> dbo_Users = new ArrayList<>();
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

		{
			for (Dbo_User dbo_User2 : garden.getUsers()) {
				
				if(dbo_User2.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_CHAUFFEUR)))
				dbo_Users.add(dbo_User2);
			}
			
			
			return ResponseEntity.ok(new MessageResponse("" + dbo_Users ));

		}
		
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

		}

	}
	//****************************************getAllChauffeur_ByGarden******************************************************//
	
	
	
	
	//****************************************getAllClasse_ByGarden******************************************************//

		public ResponseEntity<?> getAllClasse_ByGarden(long user_id,int garden_id) {
			Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
			 Garden garden =garden_Repository.findById(garden_id).orElse(null);
			 List<Classe> classes = new ArrayList<>();
			if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

			{
				for (Classe  classe  : garden.getClasses()) {
					
					classes.add(classe);
				}
				
				
				return ResponseEntity.ok(new MessageResponse("" + classes ));

			}
			else
			
			{
				return ResponseEntity.ok(new MessageResponse("user n'est pas admin "));
			}
			
			

		
		}

		
		//****************************************getAllClasse_ByGarden******************************************************//
		
		
		
		//****************************************getAllTrajets_ByGarden_ByChauffeur******************************************************//

				public ResponseEntity<?> getAllTrajets_ByGarden_ByChauffeur(long user_id,int garden_id) {
					Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
					 Garden garden =garden_Repository.findById(garden_id).orElse(null);
					 List<Trajet> trajets = new ArrayList<>();
					if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_CHAUFFEUR)))
					{
						for (Trajet trajet    : trajet_Repository.select_trajet_bychauffeur(user_id)) {
							trajets.add(trajet);
						}
						return ResponseEntity.ok(new MessageResponse("" + trajets ));
					}
					else
					{
						return ResponseEntity.ok(new MessageResponse("user n'est pas chauffeur "));
					}
				}
				//****************************************getAllTrajets_ByGarden_ByChauffeur******************************************************//
		
				
				
				//****************************************getAllenfant_ByTrajet_ByChauffeur******************************************************//

				public ResponseEntity<?> getAllenfant_ByTrajet_ByChauffeur(long user_id,int trajet_id) {
					Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
					Trajet trajet  = trajet_Repository.findById(trajet_id).orElse(null);
					 List<Dbo_User> users = new ArrayList<>();
					if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_CHAUFFEUR)))
					{
						for (Dbo_User user    : trajet_Repository.select_classes_bytrajet(trajet.getClasse().getId())) {
							
							users.add(user);
						}
						return ResponseEntity.ok(new MessageResponse("" + users ));
					}
					else
					{
						return ResponseEntity.ok(new MessageResponse("user n'est pas chauffeur "));
					}
				}
				//****************************************getAllenfant_ByTrajet_ByChauffeur******************************************************//
		
		
		
		
		
		
		
		
		
		//****************************************getAllBus_ByGarden******************************************************//

				public ResponseEntity<?> getAllBus_ByGarden(long user_id,int garden_id) {
					Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
					 Garden garden =garden_Repository.findById(garden_id).orElse(null);
					 List<Bus> bus = new ArrayList<>();
					if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

					{
						for (Bus  bus2  : garden.getBus()) {
							
							bus.add(bus2);
						}
						
						
						return ResponseEntity.ok(new MessageResponse("" + bus ));

					}
					else
					
					{
						return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

					}

				
				}

				
				//****************************************getAllBus_ByGarden******************************************************//
	
	
	
	//****************************************ajouterTrajet_byGarden******************************************************//
	public ResponseEntity<?> ajouterTrajet_byGarden(long user_id, Trajet trajet,int garden_id,int classe_id,long chauffeur_id,int bus_id) {
			Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
			Dbo_User dbo_User_chauffeur = ur1.findById(chauffeur_id).orElse(null);
			 Garden garden =garden_Repository.findById(garden_id).orElse(null);
			 Classe classe =classe_Repository.findById(classe_id).orElse(null);
			 Bus bus= bus_Repository.findById(bus_id).orElse(null);

			if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
				trajet.setDbo_User(dbo_User_chauffeur);
				trajet.setBus(bus);
				trajet.setClasse(classe);
				trajet.setGarden(garden);
				trajet_Repository.save(trajet);
					return ResponseEntity.ok(new MessageResponse("trajet est bien enregistrée"));
				}
			else {
				return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));
			}
		}
		//****************************************ajouterTrajet_byGarden******************************************************//
	
	

	

}

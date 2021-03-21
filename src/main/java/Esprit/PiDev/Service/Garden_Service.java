package Esprit.PiDev.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Classe;
import Esprit.PiDev.Entity.Dbo_Role;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Classe_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Garden_Service {
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

	/*
	 * public void affecterUserGarten(long id_user, int id_garden) { Dbo_User
	 * dbo_User = ur1.findById(id_user).orElse(null); Dbo_Role dbo_Role =
	 * role_Repository.findByName(ERole.ROLE_ADMIN).orElse(null); if
	 * (dbo_User.getRole().stream().anyMatch(e ->
	 * e.getName().equals(ERole.ROLE_ADMIN))) { System.out.println(
	 * "*********************************************affecterUserGarden*****************************************"
	 * ); System.out.println(
	 * "**************************************************************************************"
	 * ); Garden garden3 = garden_Repository.findById(id_garden).orElse(null);
	 * if (garden3 == null) { System.out.println("Garden n'exite pas "); } else
	 * { dbo_User.setGarden(garden3);
	 * System.out.println(ur1.save(dbo_User).toString());
	 * 
	 * } }
	 * 
	 * else System.out.println("user n'est pas un role admin");
	 * System.out.println(
	 * "*********************************************affecterUserGarden*****************************************"
	 * );
	 * 
	 * }
	 */

	/*
	 * public void ajouteruserGarten(Garden garden) {
	 * 
	 * System.out.println(
	 * "*********************************************ajouterUserGarden*****************************************"
	 * );
	 * 
	 * System.out.println(
	 * "**************************************************************************************"
	 * );
	 * 
	 * //Dbo_User dbo_User = ur1.findById(id_user).orElse(null); Dbo_Role
	 * dbo_Role = role_Repository.findByName(ERole.ROLE_ADMIN).orElse(null);
	 * System.out.println(
	 * "*********************Role************************************");
	 * 
	 * System.out.println(dbo_Role.toString()); System.out.println(
	 * "*********************test************************************");
	 * 
	 * if (dbo_User.getRole().stream().anyMatch(e ->
	 * e.getName().equals(ERole.ROLE_ADMIN))) { System.out.println(
	 * "*********************************************ajouterGarden*****************************************"
	 * ); System.out.println(
	 * "**************************************************************************************"
	 * ); Garden garden2 = garden_Repository.findByPhone(garden.getPhone());
	 * Garden garden3 =
	 * garden_Repository.findByDescription(garden.getDescription()); if (garden2
	 * != null || garden3 != null) { System.out.println("Garden existe deja"); }
	 * else { System.out.println(garden_Repository.save(garden).toString());
	 * dbo_User.setGarden(garden);
	 * System.out.println(ur1.save(dbo_User).toString());
	 * 
	 * } }
	 * 
	 * else System.out.println("user n'est pas un role admin");
	 * System.out.println(
	 * "*********************************************ajouterUserGarden*****************************************"
	 * ); }
	 */

	public ResponseEntity<?> getAllGarden(long user_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN)))

		{
			return ResponseEntity.ok(new MessageResponse("" + garden_Repository.findAll()));

		}
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

		}

	}

	public ResponseEntity<?> ajouterGarden(long user_id, Garden garden) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			Garden garden4 = garden_Repository.findByName(garden.getName());
			if (garden4 != null) {
				return ResponseEntity.ok(new MessageResponse("garden existe déja"));

			}

			else {
				garden_Repository.save(garden);
				return ResponseEntity.ok(new MessageResponse("garden est bien enregistrée"));

			}

		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}

	public ResponseEntity<?> DeleteGarden(long user_id, int garden_id) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			 Garden garden =garden_Repository.findById(garden_id).orElse(null);
			if ( garden != null) {
				garden_Repository.deleteById(garden_id);
				return ResponseEntity.ok(new MessageResponse("garden est supprimé "));
			} else {
				return ResponseEntity.ok(new MessageResponse("garden n'existe pas"));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}
	
	public ResponseEntity<?> UpdateGarden(long user_id, int garden_id,Garden garden) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			 Garden garden_To_Update =garden_Repository.findById(garden_id).orElse(null);
			if ( garden_To_Update != null) {
				
				garden_To_Update.setDescription(garden.getDescription());
				garden_To_Update.setLocation(garden.getLocation());
				garden_To_Update.setPhone(garden.getPhone());
				garden_To_Update.setEmail(garden.getEmail());
				garden_To_Update.setName(garden.getName());
				garden_To_Update.setPrice(garden.getPrice());
				
				garden_Repository.save(garden_To_Update);
				return ResponseEntity.ok(new MessageResponse("garden est bien modifiée "));
			} else {
				return ResponseEntity.ok(new MessageResponse("garden n'existe pas"));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}
	
	

	public HashSet<Dbo_User> ajouterparentenfant(long id_parent, HashSet<Dbo_User> users) throws ParseException {
		System.out.println("********************************ajouterparentenfant************************************");
		Dbo_User dbo_User = ur1.findById(id_parent).orElse(null);
		Dbo_Role dbo_Role = role_Repository.findByName(ERole.ROLE_PARENT).orElse(null);

		System.out.println(id_parent);

		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			Dbo_Role role = new Dbo_Role(ERole.ROLE_ENFANT);
			if ((role_Repository.findByName(ERole.ROLE_ENFANT)) == null) {

				ur.saveOrUpdate(role);
			}

			for (Dbo_User dbo_User2 : users) {
				Set<Dbo_Role> role1 = new HashSet<>();
				Dbo_Role dbo_Role1 = role_Repository.findByName(ERole.ROLE_ENFANT).orElse(null);
				role1.add(role);
				dbo_User2.getRole().addAll(role1);
				dbo_User2.setPassword(dbo_User.getPassword());
				//dbo_User2.setParent_id(dbo_User.getId());
				userService.saveOrUpdate(dbo_User2);
			}
		}

		else
			System.out.println("user n'est pas un role parent");
		return users;

	}

	public ResponseEntity<?> affecter_enfant_parent_jardin(long id_parent, HashSet<Dbo_User> users, int garden_id)
			throws ParseException {

		List<Classe> listclasses = (List<Classe>) classe_Repository.findAll();
		int trouve = classe_Repository.existe_garden_id(garden_id);

		Dbo_User dbo_User = ur1.findById(id_parent).orElse(null);
		Garden garden = garden_Repository.findById(garden_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {

			Dbo_Role role = new Dbo_Role(ERole.ROLE_ENFANT);
			if ((role_Repository.findByName(ERole.ROLE_ENFANT)) == null) {

				ur.saveOrUpdate(role);
			}

			for (Dbo_User dbo_User2 : users) {
				Set<Dbo_Role> role1 = new HashSet<>();
				Dbo_Role dbo_Role1 = role_Repository.findByName(ERole.ROLE_ENFANT).orElse(null);
				role1.add(dbo_Role1);
				dbo_User2.setRole(role1);
				dbo_User2.setPassword(dbo_User.getPassword());
				//dbo_User2.setParent_id(dbo_User.getId());
				dbo_User2.setGarden(garden);

				if (listclasses.size() == 0 || trouve == 0)

				{
					System.out.println("***********************************listclasses.size() ==0****************************");
					Classe classe = new Classe();
					classe.setName("A" + 0);
					classe.setCapacite(classe.getCapacite() - 1);
					classe.setGarden(garden);
					classe.setDescription("**********");
					classe.setCompteur(0);
					Classe classe2 = classe_Repository.save(classe);
					dbo_User2.setClasse(classe2);
					userService.saveOrUpdate(dbo_User2);
					System.out.println("************************listclasses.size() == 0***************************************");

				}

				else if (listclasses.size() > 0) {
					System.out.println("***********************************listclasses.size()****************************");

					Classe classe_test = classe_Repository.findCapacitebygarden(garden_id).stream().findFirst().orElse(null);
					if (classe_test != null) {
						classe_test.setName(classe_test.getName());
						classe_test.setCapacite(classe_test.getCapacite() - 1);
						classe_test.setGarden(garden);
						classe_test.setName("A"+ classe_Repository.max_compteur_garden(garden_id));
						classe_test.setDescription("**********");
						classe_test.setCompteur(classe_Repository.max_compteur_garden(garden_id));
						Classe classe2 = classe_Repository.save(classe_test);
						dbo_User2.setClasse(classe2);
						userService.saveOrUpdate(dbo_User2);


					} else {
						System.out.println("***********************************listclasses.size()****************************");

						Classe classe = new Classe();
						classe.setName("A"+classe_Repository.max_compteur_garden(garden_id)+1);
						classe.setCapacite(classe.getCapacite() - 1);
						classe.setGarden(garden);
						classe.setDescription("**********");
						classe.setCompteur(classe_Repository.max_compteur_garden(garden_id) +1);

						Classe classe2 = classe_Repository.save(classe);
						dbo_User2.setClasse(classe2);

						userService.saveOrUpdate(dbo_User2);

						System.out.println("***********************************else****************************");


					}

				}

			}

			return ResponseEntity.ok(new MessageResponse("enfant effectue avec succes"));

		} else {
			return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));
		}

	}

	
	
	
	public ResponseEntity<?> afficher_enfant_byparent(Long id_parent) {

		Dbo_User dbo_User = ur1.findById(id_parent).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {

			//return ResponseEntity.ok(
					//new MessageResponse("les enfants sont affiches: \n  " + ur1.afficher_enfant_byparent(id_parent)));

		} else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));
		}
		return null;
	}

	public ResponseEntity<?> afficher_enfant_byparent(Long id_parent, int garden) {

		Dbo_User dbo_User = ur1.findById(id_parent).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {

		//	return ResponseEntity.ok(new MessageResponse(
				//	"les enfants sont affiches: \n  " + ur1.afficher_enfant_byParent(id_parent, garden)));

		} else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));
		}
		return null;
	}

	public ResponseEntity<?> select_parent_by_Garden(int id) {
		List<Dbo_User> dbo_Users = new ArrayList<>();
		for (Long element : garden_Repository.select_parent_by_Garden(id)) {

			Dbo_User user = ur1.findById(element).orElse(null);
			dbo_Users.add(user);
		}

		return ResponseEntity.ok(new MessageResponse("les parents sont affiches: \n  " + dbo_Users));

	}

	public ResponseEntity<?> select_enfant_parent_by_Garden(int garden_id, long parent_id1) {

		List<Long> list = garden_Repository.select_parent_by_Garden(garden_id);
		if (list.contains(parent_id1)) {

			return afficher_enfant_byparent(parent_id1, garden_id);
		} else
			return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));

		// return ur1.afficher_enfant_byparent(parent_id1);
	}

	public ResponseEntity<?> select_enfant_by_Garden(int garden_id) {
	List<Dbo_User> dbo_Users = new ArrayList<>();
	
	Garden garden = garden_Repository.findById(garden_id).orElse(null);
	
	if(garden != null)
	{
		for (Dbo_User dbo_User : garden.getUsers()) {
			
			if(dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ENFANT)))
					{
				dbo_Users.add(dbo_User);
				
				
				
					}
			
		}
		
	}
	else
	
		return ResponseEntity.ok(new MessageResponse("garden existe pas"));


	return ResponseEntity.ok(new MessageResponse("les enfants sont :"+dbo_Users ));

	}

	

	
	

	
}

/*
 * public String ajouterparentenfant( long id_parent ) throws ParseException {
 * String a = null; System.out.println(
 * "********************************ajouterparentenfant************************************"
 * ); Dbo_User dbo_User = ur1.findById(id_parent).orElse(null); Dbo_Role
 * dbo_Role = role_Repository.findByName(ERole.PARENT).orElse(null);
 * 
 * System.out.println(id_parent);
 * 
 * 
 * if (dbo_User.getRole().stream().anyMatch(e ->
 * e.getName().equals(ERole.PARENT))) { // System.out.println(
 * "**************************************************************************************"
 * ); /*Dbo_Role role = new Dbo_Role(ERole.ENFANT); ur.saveOrUpdate(role); for
 * (Dbo_User dbo_User2 : list_enfant) { HashSet<Dbo_Role> role1 = new
 * HashSet<>(); Dbo_Role dbo_Role1
 * =role_Repository.findByName(ERole.ENFANT).orElse(null); role1.add(role);
 * dbo_User2.setRole(role1);
 * 
 * //dbo_User2.getRole().addAll(role1); //dbo_User2.getRole().add(dbo_Role1);
 * 
 * // dbo_User2.setEmail("enfant@gmail.com");
 */

// a= dbo_User.getEmail();

// System.out.println("*********************************setParent*****************************************************");

/*
 * dbo_User2.setParent_id(dbo_User.getId());
 * System.out.println(dbo_User2.toString());
 * 
 * System.out.println(
 * "*********************************setParent*****************************************************"
 * );
 * 
 * userService.saveOrUpdate(dbo_User2);
 * 
 * 
 * System.out.println(
 * "***********************saveOrUpdate********************************");
 * 
 * }
 * 
 * 
 * 
 * 
 * }
 */
// else
// System.out.println("user n'est pas un role parent");
// System.out.println("*********************************************ajouterUserGarden*****************************************");

// System.out.println("********************************ajouterparentenfant************************************");

// }
// return a;
// }*/

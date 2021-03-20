package Esprit.PiDev.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Activity;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Activity_Repository;
import Esprit.PiDev.Repository.Classe_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Activity_Service {
	
	
	@Autowired 
	Activity_Repository activity_Repository;
	
	
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
	
	
	
	/*-------------------------------GetAllActivity--------------------------------------------------*/

	public ResponseEntity<?> GetAllActivity(long user_id)
	{
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
		 List<Activity> activities =(List<Activity>) activity_Repository.findAll();
			return ResponseEntity.ok(new MessageResponse(""+activities));

		}
		 else {

				return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

			}
	}
	
	/*-------------------------------GetAllActivity--------------------------------------------------*/

	
	
	
	
	/*-------------------------------AjouterActivity--------------------------------------------------*/

	
	public ResponseEntity<?> AjouterActivity(long user_id,Activity activity)
	{
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			return ResponseEntity.ok(new MessageResponse(""+activity_Repository.save(activity)));

		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}
	}
	/*-------------------------------AjouterActivity--------------------------------------------------*/

	
	
	
	
	
	/*-------------------------------DeleteActivity--------------------------------------------------*/

	
	public  ResponseEntity<?>  DeleteActivity(long user_id,int activity_id)
	{
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
		  activity_Repository.deleteById(activity_id);
		  return ResponseEntity.ok(new MessageResponse("activity est bien supprimée "));
		  
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}
	}
	
	
	/*-------------------------------DeleteActivity--------------------------------------------------*/

	
	
	
	/*-------------------------------UpdateActivity--------------------------------------------------*/

	
	public ResponseEntity<?> UpdateActivity(long user_id, int activity_id,Activity activity) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			 Activity activity_To_Update =activity_Repository.findById(activity_id).orElse(null);
			if ( activity_To_Update != null) {
				
				activity_To_Update.setDescription(activity.getDescription());
				activity_To_Update.setTitle(activity.getTitle());
				activity_To_Update.setDate(activity.getDate());
				
				activity_Repository.save(activity_To_Update);
				return ResponseEntity.ok(new MessageResponse("activity est bien modifiée "));
			} else {
				return ResponseEntity.ok(new MessageResponse("activity n'existe pas"));
			}
		}

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}
	
	/*-------------------------------UpdateActivity--------------------------------------------------*/
	
	
	
	

	
	
	/*-------------------------------SearchActivity--------------------------------------------------*/

	
	public ResponseEntity<?> SearchActivity(long user_id,String search) {
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
			List<Activity> activities_search =activity_Repository.findByTitle(search);
	
				return ResponseEntity.ok(new MessageResponse(""+activities_search));

			}
		

		else {

			return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

		}

	}
	/*-------------------------------SearchActivity--------------------------------------------------*/

	
	
	

}

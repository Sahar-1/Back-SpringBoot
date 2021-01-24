package Esprit.PiDev.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.User_Repository;
import net.minidev.json.JSONObject;
 
 

@Service
public class User_Service implements Interface_User_Service {

	Logger logger_Service = Logger.getLogger(this.getClass().getName());

	@Autowired
	private User_Repository Jpa_User_Repository;
	
	@Override
	public Dbo_User addUser(Dbo_User userToAdd) {
 
		return Jpa_User_Repository.save(userToAdd);
	}

	@Override
	public List<Dbo_User> retrieveAllUsers() {
 		return (List<Dbo_User>) Jpa_User_Repository.findAll();
	}

	@Override
	public Optional<Dbo_User> findById(Long id) {
		return Jpa_User_Repository.findById(id);
	}

	@Override
	public Dbo_User saveOrUpdate(Dbo_User user) {
 		return Jpa_User_Repository.save(user);
	}

	@Override
	public String deleteById(Long id) {
		 
		JSONObject jsonObject = new JSONObject();
		try {
			Jpa_User_Repository.deleteById(id);
			jsonObject.put("message", "User deleted successfully ");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return jsonObject.toString();
 	}
}

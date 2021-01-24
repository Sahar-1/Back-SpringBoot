package Esprit.PiDev.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Dbo_Role;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;
 

@CrossOrigin("*")
@RestController

public class User_Controller_Rest_Web_Service {

	@Autowired
	private Interface_User_Service I_User_Service;
	@Autowired
	User_Repository Jpa_User_Repository;
	@Autowired
	Role_Repository Jpa_Role_Repository;

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return "Welcome to RestTemplate Esprit_Pi_Dev.";
	}

	@RequestMapping(value = "/add")
	public Dbo_User Add_User(@RequestBody Dbo_User userToAdd, @RequestBody Dbo_Role dboR) {

		return I_User_Service.addUser(userToAdd);

	}

	@GetMapping("/retrieve-all-users")
	public List<Dbo_User> getUsers() {
		List<Dbo_User> list = I_User_Service.retrieveAllUsers();
		return list;
	}
	 @GetMapping("/retrieve-user/{user-id}")
	   public Optional< Dbo_User> retrieveUserById(@PathVariable("user-id") Long userId , HttpServletRequest request, HttpServletResponse response) throws UserPrincipalNotFoundException {
		   if(I_User_Service.findById(userId)==null)
		   {
			   throw new UserPrincipalNotFoundException("Identify not matched or user not found");
		   }
		   return  I_User_Service.findById(userId);
	   } 
	@RequestMapping(value="/logmeout", method = RequestMethod.POST)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	if (auth != null){
	new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	return "redirect:/login";
	}
	
	 @DeleteMapping("/remove-user/{user-id}") 
	   public void removeUser(@PathVariable("user-id") Long userId) { 
	      Jpa_User_Repository.deleteById(userId);
	   }
	 
	 
	 @RequestMapping("/adduserwithrole/{role}")
	 public Dbo_User add_user(@RequestBody Dbo_User dbo_User ,@PathVariable ("role") String role)
	 {
		 Dbo_Role dbo_Role = Jpa_Role_Repository.findByName(role);
		 dbo_User.setRole(dbo_Role);
		 Jpa_User_Repository.save(dbo_User);
		return dbo_User;
		 
	 }
	 
	 @PutMapping("/updateUser/{user-id}")
	 public Dbo_User updateUser(@RequestBody Dbo_User dbo_User , @PathVariable("user-id") Long id )
	 {
		 Dbo_User userToUpdate = Jpa_User_Repository.findById(id).orElse(null);
	//return	Jpa_User_Repository.save(dbo_User);
	String lastName = dbo_User.getLastName();userToUpdate.setLastName(lastName);
	String firstname = dbo_User.getFirstName();userToUpdate.setFirstName(firstname);
	Date date = dbo_User.getDate();userToUpdate.setDate(date);
	boolean actif = dbo_User.isActif();userToUpdate.setActif(actif);
	if(dbo_User.getRole()==null)
	{
		userToUpdate.setRole(userToUpdate.getRole());
	}
	 

		   
	  return Jpa_User_Repository.save(userToUpdate);
				   
	}

}

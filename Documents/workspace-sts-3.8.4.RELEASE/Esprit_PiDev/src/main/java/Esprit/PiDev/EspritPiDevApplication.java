package Esprit.PiDev;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.InterfaceService.Interface_User_Role_Service;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;

@SpringBootApplication
public class EspritPiDevApplication implements CommandLineRunner{

	@Autowired
	Interface_User_Service userService;
	@Autowired
	Interface_User_Role_Service roleService;
	@Autowired
	User_Repository us;
	@Autowired
	Role_Repository ur;
	public static void main(String[] args) {
		SpringApplication.run(EspritPiDevApplication.class, args);
		 
	}
	
	@Override
	public void run(String... args) throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("1995-03-05");
		
		//Dbo_Role role = new Dbo_Role("SimpleUser");
			 
			//Dbo_Role dbo= ur.saveAndFlush(role);
		
		Dbo_User user = new Dbo_User();
			user.setFirstName("Khalil");
			user.setLastName("Boutar");
			user.setEmail("khalil.boutar@esprit.tn");
			user.setDate(d);
			user.setPassword(new BCryptPasswordEncoder().encode("123456"));
			user.setActif(true);
			//user.setRole(dbo);
			//us.saveAndFlush(user);
	}
}

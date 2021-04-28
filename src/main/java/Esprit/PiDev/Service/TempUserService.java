package Esprit.PiDev.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class TempUserService {
	
	@Autowired 
	User_Repository userRepo ;
	
	public Dbo_User getConnectedUser(){
		return userRepo.findByEmail("ons.bouguerra@esprit.tn");
	}

}

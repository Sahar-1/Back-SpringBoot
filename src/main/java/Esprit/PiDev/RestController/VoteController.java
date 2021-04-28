package Esprit.PiDev.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Vote;
import Esprit.PiDev.Service.VoteService;


@RestController
@RequestMapping("/vote")
public class VoteController {
	
	@Autowired
	VoteService voteService;
	

	@PostMapping("/addVote/{idSubject}/{idUser}")
	public void addVote(@RequestBody Vote vote, @PathVariable int idSubject, @PathVariable int idUser){
		voteService.addVote(vote, idUser, idSubject);
	}
	
	

}

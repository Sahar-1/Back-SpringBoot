package Esprit.PiDev.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Contract;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Contract_Service;
import Esprit.PiDev.Repository.Contract_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Service.Session_UserDetails;


@RestController
@RequestMapping("/Contract")
public class ContactRestController {
	
	@Autowired
	Contract_Repository cont_rep;
	@Autowired
	Garden_Repository gard_rep;
	
	@Autowired 
	Contract_Service cont_srv ;
	
	@RequestMapping("/addcontract/{idgarden}")
	@ResponseBody
	public ResponseEntity<?> addContract(Authentication auth,@RequestBody Contract contract,@PathVariable("idgarden") Long idgard)
	{
		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		Contract cont = new Contract();
		Garden gard=gard_rep.findById(idgard).orElseThrow( ()-> new RuntimeException("null id"));
		cont.setNameKinderGarten( contract.getNameKinderGarten());
		cont.setDate_deadline(contract.getDate_deadline());
		cont.setDate_Start(contract.getDate_Start());
		cont.setPrix(contract.getPrix());
		cont.setType(contract.getType());
		cont.setGarden(gard);
		cont_rep.save(cont);
		//cont_srv.Add_Contract(userDetails.getId(),cont,idgard);
		 return ResponseEntity.ok(new MessageResponse("success"  ));

			
	}
	
	@GetMapping("/Retrieve_All_Contracts")
	public ResponseEntity<?> getAllContracts(Authentication auth)
  {
	
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return cont_srv.getAllContracts(userDetails.getId());

		
  }
	@DeleteMapping("/remove_contract/{Contract_id}")
	public ResponseEntity<?> DeleteContract(Authentication auth, @PathVariable int Contract_id) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return cont_srv.Delete_Contract(userDetails.getId(), Contract_id);
	}
	@PutMapping("/modify-Contract/{contract_id}")
	public ResponseEntity<?> UpdateContract(Authentication auth, @PathVariable("contract_id") Long Contract_id,@RequestBody Contract Contract) {

		SecurityContextHolder.getContext().setAuthentication(auth);
		Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
		return cont_srv.UpdateContract(userDetails.getId(), Contract_id,Contract);
	}
	
}

package Esprit.PiDev.RestController;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Trajet;
import Esprit.PiDev.Service.Session_UserDetails;
import Esprit.PiDev.Service.Trajet_Service;

@RestController
public class Trajet_Controller {
	
	@Autowired
	Trajet_Service trajetService;
	
	// ****************************ajouterTrajet_byGarden********************************//
//		@Secured(value = {"PARENT"})
		@PostMapping("/ajouterTrajet_byGarden/{garden_id}/{classe_id}/{chauffeur_id}/{bus_id}")
		public ResponseEntity<?> ajouter_Parent_rendezVous(Authentication auth,@PathVariable("garden_id") int garden_id,@PathVariable("classe_id") int classe_id,@PathVariable("chauffeur_id") long chauffeur_id,@PathVariable("bus_id") int bus_id,@RequestBody Trajet trajet) throws ParseException {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return trajetService.ajouterTrajet_byGarden(userDetails.getId(),trajet,garden_id,classe_id,chauffeur_id,bus_id);
		}
	// ****************************ajouter_Parent_rendezVous********************************//
		
		
		
		
		// ****************************getAllChauffeur_ByGarden********************************//
//		@Secured(value = {"PARENT"})
		@GetMapping("/getAllChauffeur_ByGarden/{garden_id}")
		public ResponseEntity<?> getAllChauffeur_ByGarden(Authentication auth,@PathVariable("garden_id") int garden_id) throws ParseException {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return trajetService.getAllChauffeur_ByGarden(userDetails.getId(),garden_id);
		}
	// ****************************getAllChauffeur_ByGarden********************************//
		
		
		
		// ****************************getAllClasse_ByGarden********************************//
//		@Secured(value = {"PARENT"})
		@GetMapping("/getAllClasse_ByGarden/{garden_id}")
		public ResponseEntity<?> getAllClasse_ByGarden(Authentication auth,@PathVariable("garden_id") int garden_id) throws ParseException {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return trajetService.getAllClasse_ByGarden(userDetails.getId(),garden_id);
		}
	// ****************************getAllClasse_ByGarden********************************//
		
		
		
		
		
		// ****************************getAllenfant_ByTrajet_ByChauffeur********************************//
//		@Secured(value = {"PARENT"})
		@GetMapping("/getAllenfant_ByTrajet_ByChauffeur/{trajet_id}")
		public ResponseEntity<?> getAllenfant_ByTrajet_ByChauffeur(Authentication auth,@PathVariable("trajet_id") int trajet_id) throws ParseException {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return trajetService.getAllenfant_ByTrajet_ByChauffeur(userDetails.getId(),trajet_id);
		}
	// ****************************getAllenfant_ByTrajet_ByChauffeur********************************//
		
		
		
		
		
		
		
		
		
		
		
		// ****************************getAllTrajets_ByGarden_ByChauffeur********************************//
//		@Secured(value = {"PARENT"})
		@GetMapping("/getAllTrajets_ByGarden_ByChauffeur/{garden_id}")
		public ResponseEntity<?> getAllTrajets_ByGarden_ByChauffeur(Authentication auth,@PathVariable("garden_id") int garden_id) throws ParseException {
			SecurityContextHolder.getContext().setAuthentication(auth);
			Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();
			return trajetService.getAllTrajets_ByGarden_ByChauffeur(userDetails.getId(),garden_id);
		}
	// ****************************getAllTrajets_ByGarden_ByChauffeur********************************//
		
		
		

}

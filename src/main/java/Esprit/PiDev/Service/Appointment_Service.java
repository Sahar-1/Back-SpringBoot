package Esprit.PiDev.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Appointment;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.Garden;
import Esprit.PiDev.Entity.Historique;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Appointment_Repository;
import Esprit.PiDev.Repository.Classe_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.Historique_Repository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service
public class Appointment_Service {
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
	Appointment_Repository appointment_Repository;
	@Autowired
	 Email_Sender_Service emailSenderService;
	@Autowired
	Historique_Repository historique_Repository;
	
	//****************************************ajouter_Parent_rendezVous******************************************************//

	public ResponseEntity<?> ajouter_Parent_rendezVous(Long id_parent, int garden_id,Appointment appointment) {

		// TODO Auto-generated method stub
		Dbo_User dbo_User = ur1.findById(id_parent).orElse(null);
		Garden garden = garden_Repository.findById(garden_id).orElse(null);
		List<Integer> hour= new ArrayList<Integer>();
		List<String> compteur= new ArrayList<String>();
	//	hour.add(8);hour.add(9); hour.add(10);hour.add(11);hour.add(12);hour.add(13);hour.add(14);hour.add(15);hour.add(16);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT))) {
			for (Appointment appointment1 : appointment_Repository.find_date_appointment_bygarden(appointment.getDate(),garden.getId())) {
				compteur.add(appointment1.getBeginhour());
			}
				if(compteur.contains(appointment.getBeginhour()))
				{
				return	ResponseEntity.ok(new MessageResponse("hour existe"));

				}
				else
				{


					appointment.setGarden(garden);
					appointment.setUser(dbo_User);
					appointment.setStatus(0);
					appointment.setEndhour(Integer.toString(Integer.parseInt(appointment.getBeginhour()) +1));
					 Appointment appointment2 = appointment_Repository.save(appointment);
					 return ResponseEntity.ok(new MessageResponse("   " +appointment2));


				}

					
				
				
		
			
		}
		else
	{
		return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));
		}
	
	}
	//****************************************ajouter_Parent_rendezVous******************************************************//

	
	
	
	//****************************************ajouter_admin_rendezVous******************************************************//

		public ResponseEntity<?> ajouter_admin_rendezVous(Long id_admin, int id_parent, int garden_id,Appointment appointment) {
		
			Dbo_User dbo_User = ur1.findById(id_admin).orElse(null);
			Garden garden = garden_Repository.findById(garden_id).orElse(null);
			Dbo_User user = ur1.findById((long) id_parent).orElse(null);

			if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
				if(user.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_PARENT)))
						{
				
				appointment.setGarden(garden);
				appointment.setUser(user);
				appointment.setStatus(1);
				appointment.setEndhour(Integer.toString(Integer.parseInt(appointment.getBeginhour()) +1));
				 Appointment appointment2 = appointment_Repository.save(appointment);
				 return ResponseEntity.ok(new MessageResponse("" +appointment2));
						}
				else
				{
				return ResponseEntity.ok(new MessageResponse("user n'est pas parent"));
				}

			}
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

			
			
			
			
		}
		
		
		//****************************************ajouter_admin_rendezVous******************************************************//
	
		
		
		
	
	
	
	
	
	
		
		
		//****************************************getall_appointment_bygarden******************************************************//

				public ResponseEntity<?> getall_appointment_bygarden(Long user_id,int garden_id) {
					Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
					Garden garden = garden_Repository.findById(garden_id).orElse(null);
					List<Appointment> appointments = new ArrayList<>();

					if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
						
						if(garden == null)
						{
							return ResponseEntity.ok(new MessageResponse("garden n'existe pas"));

						}
						else
						{
							
						
						for (Appointment appointment : garden.getAppointments()) {
							appointments.add(appointment);
							
					}
						return ResponseEntity.ok(new MessageResponse(""+appointments));

					}
					}
					else
					{
						return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

					}
					

						
					}
 
				//****************************************getall_appointment_bygarden******************************************************//



				//****************************************delete_appointment******************************************************//

						public ResponseEntity<?> delete_appointment(Long user_id,int id_appointment) {
						
							Appointment appointment = appointment_Repository.findById(id_appointment).orElse(null);
							if(appointment == null)
							{
								return ResponseEntity.ok("appointment n'existe pas");	

							}
							else
							{
								appointment_Repository.deleteById(appointment.getId());
								return ResponseEntity.ok(new MessageResponse("appointment est supprimé"));	
							}
							}
		
						//****************************************delete_appointment******************************************************//
	
	
			//****************************************update_appointment******************************************************//

						
						public ResponseEntity<?> update_appointment(long user_id, int appointment_id,Appointment appointment) {
							Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
							if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
								 Appointment appointment_To_Update =appointment_Repository.findById(appointment_id).orElse(null);
								if ( appointment_To_Update != null) {
									
									appointment_To_Update.setBeginhour(appointment.getBeginhour());
									appointment_To_Update.setDate(appointment.getDate());
									appointment_To_Update.setDescription(appointment.getDescription());
									appointment_To_Update.setEndhour(Integer.toString(Integer.parseInt(appointment.getBeginhour()) +1));
									appointment_To_Update.setStatus(1);

									
									appointment_Repository.save(appointment_To_Update);
									return ResponseEntity.ok(new MessageResponse("appointment est bien modifiée "));
								} else {
									return ResponseEntity.ok(new MessageResponse("appointment n'existe pas"));
								}
							}

							else {

								return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

							}

						}
						
						//****************************************update_appointment******************************************************//
	
	
	
						
						
						//****************************************searchappointment******************************************************//

						
						public ResponseEntity<?> searchappointment(long user_id,String search) {
							Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
							if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
								List<Appointment> appointment_search =appointment_Repository.searchappointment(search);
						
									return ResponseEntity.ok(new MessageResponse(""+appointment_search));

								}
							

							else {

								return ResponseEntity.ok(new MessageResponse("user n'est pas un admin"));

							}

						}
						//****************************************searchappointment******************************************************//
					
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
	
	
	
	

	//****************************************getallappointment_year******************************************************//

	public ResponseEntity<?> getallappointment_year(Long user_id,int year) {
	
		Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
		if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
		return ResponseEntity.ok(new MessageResponse("" +appointment_Repository.find_appointment_year(year)));
		}
		else
		{
			return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));
		}
		
		
		
	}
	
	
	//****************************************getallappointment_year******************************************************//
	
	
	//****************************************getallappointment_status_1******************************************************//
		public ResponseEntity<?> getallappointment_status_1(Long id_medecin) {
			
			Dbo_User dbo_User = ur1.findById(id_medecin).orElse(null);
			if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_MEDECIN))) {
			List<Appointment> appointments = new ArrayList<>();
			List<Appointment> appointments2 = (List<Appointment>) appointment_Repository.findAll();
			for (Appointment appointment : appointments2) {
				if(appointment.getStatus() == 1)
				{
					appointments.add(appointment);
				}
			}
			return ResponseEntity.ok(new MessageResponse("" +appointments));
			}
			else
			{
				return ResponseEntity.ok(new MessageResponse("user n'est pas medecin"));
			}
		}
		//****************************************getallappointment_status_1******************************************************//
		
		
		//****************************************getallappointment_status_0******************************************************//
				public List<Appointment> getallappointment_status_0(Long id_admin) {
					
					Dbo_User dbo_User = ur1.findById(id_admin).orElse(null);
					if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
					List<Appointment> appointments = new ArrayList<>();
					List<Appointment> appointments2 = (List<Appointment>) appointment_Repository.findAll();
					for (Appointment appointment : appointments2) {
						if(appointment.getStatus() == 1)
						{
							appointments.add(appointment);
						}
					}
					
					return appointments;

					
					
			
				}
					return null;
				}
				//****************************************getallappointment_status_0******************************************************//


			
			

			//****************************************accepte_appointment******************************************************//

			public ResponseEntity<?> accepte_appointment(Long user_id,int id_appointment) {
			
				Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
				Appointment appointment = appointment_Repository.findById(id_appointment).orElse(null);
				if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
					if(appointment.getStatus() == 0)
					{
						appointment.setStatus(1);
						appointment_Repository.save(appointment);
						
						SimpleMailMessage mailMessage = new SimpleMailMessage();
						mailMessage.setTo(appointment.getUser().getEmail());
						mailMessage.setSubject("!! appointment Information !!");
						mailMessage.setFrom("ayoub.benzahra@esprit.tn");
						mailMessage.setText(" Dear Mr "+appointment.getUser().getFirstName()+"  "+appointment.getUser().getLastName()+
											"    your appointment is  accepted ,  in  "+appointment.getDate()+ "  at  "+appointment.getBeginhour());

						emailSenderService.sendEmail(mailMessage);
						return ResponseEntity.ok(new MessageResponse("appointment est  accépté"));


					}
					else
					{
						return ResponseEntity.ok(new MessageResponse("appointment est  déja vaildé"));

					}
					
				}
				else
				{
					return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));
				}
				
			}
			
			
			//****************************************accepte_appointment******************************************************//
			
			
			//****************************************refut_appointment******************************************************//

				public ResponseEntity<?> refut_appointment(Long user_id,int id_appointment) {
				
					Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
					Appointment appointment = appointment_Repository.findById(id_appointment).orElse(null);
					if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
						if(appointment.getStatus() == 0)
						{
							
							SimpleMailMessage mailMessage = new SimpleMailMessage();
							mailMessage.setTo(appointment.getUser().getEmail());
							mailMessage.setSubject("!! appointment Information !!");
							mailMessage.setFrom("ayoub.benzahra@esprit.tn");
							mailMessage.setText(" Dear Mr "+appointment.getUser().getFirstName()+"  "+appointment.getUser().getLastName()+
												"    your appointment is  not  accepted ,  in  "+appointment.getDate());

							emailSenderService.sendEmail(mailMessage);
							
							appointment_Repository.deleteById(appointment.getId());
							return ResponseEntity.ok(new MessageResponse("appointment est réfusé"));

						}
						else{
							return ResponseEntity.ok(new MessageResponse("appointment est accepté deja"));

						}

					}
					else
					{
						return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));
					}
							}
				
				
				//****************************************refut_appointment******************************************************//
				
				
				
				
				
				//****************************************ajouter_note_appointment_medecin******************************************************//

				public ResponseEntity<?> ajouter_note_appointment_medecin(Long user_id,int id_appointment, Historique historique) {
				
					Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
					Appointment appointment = appointment_Repository.findById(id_appointment).orElse(null);
					if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_MEDECIN))) {
					
						historique.setAppointment(appointment);
						historique_Repository.save(historique);
						
						return ResponseEntity.ok(new MessageResponse("note est enregistrée"));
					}
						else
						{
							return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));
						}
								}
				
				//****************************************ajouter_note_appointment_medecin******************************************************//		
				
	
				
				
				//****************************************lister_appointment_historique_byparent******************************************************//

				public ResponseEntity<?> lister_appointment_historique_byparent(Long user_id,long id_parent) {
				
					Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
					Dbo_User user = ur1.findById((long) id_parent).orElse(null);
					List<Appointment> appointments = new ArrayList<>();
					List<Historique> historiques = new ArrayList<>();

					if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_MEDECIN))) {
				
						String response = "";
						for (Appointment appointment : appointment_Repository.find_appointment_byparent(id_parent)) {
							
							for (Historique historique : appointment.getHistoriques()) {
			response = response + " appointment est  "+ appointment.getDate()+ "  les historique est "+historique.getDescription()+""
					+ " ";
								
							} 
							
							
						}
						return ResponseEntity.ok(new MessageResponse(response));
					}
						else
						{
							return ResponseEntity.ok(new MessageResponse("user n'est pas medecin"));
						}
								}
				
				//****************************************lister_appointment_historique_byparent******************************************************//
				
				
				
				//****************************************lister_date_disponible_bygarden******************************************************//

				public ResponseEntity<?> lister_date_disponible_bygarden(Long user_id,int id_garden,String date) throws ParseException {
				
					Dbo_User dbo_User = ur1.findById(user_id).orElse(null);
					Garden garden = garden_Repository.findById(id_garden).orElse(null);
					List<String> appointments = new ArrayList<>();
					List<String> date_disponibles = new ArrayList<>();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date d = dateFormat.parse(date);
					List<Integer> hour= new ArrayList<Integer>();
					hour.add(8);hour.add(9); hour.add(10);hour.add(11);hour.add(12);hour.add(13);hour.add(14);hour.add(15);hour.add(16);
					if (dbo_User.getRole().stream().anyMatch(e -> e.getName().equals(ERole.ROLE_ADMIN))) {
						
					for (Appointment appointment : appointment_Repository.find_date_appointment_bygarden(d,garden.getId())) {
						System.out.println("*************"+Integer.parseInt(appointment.getBeginhour()));
						if( hour.contains(Integer.parseInt(appointment.getBeginhour()) ))
						{
							System.out.println("***************************");
							hour.remove(hour.indexOf(Integer.parseInt(appointment.getBeginhour())) );
						
							
						}
						
						
						
					}
					return ResponseEntity.ok(new MessageResponse("  "+ hour));
					}
						
						else
						{
							return ResponseEntity.ok(new MessageResponse("user n'est pas admin"));

						}
								}
				
				//****************************************lister_date_disponible_bygarden******************************************************//		
				
				
				
				
				
				

	

}

package Esprit.PiDev.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Esprit.PiDev.Entity.Confirmation_Token_User;
import Esprit.PiDev.Entity.Dbo_Role;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.Dbo_User_Provider;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.ConfirmationTokenRepository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;

@Service("I_User_Service")
public class User_Service implements Interface_User_Service {

	Logger logger_Service = Logger.getLogger(this.getClass().getName());
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private User_Repository Jpa_User_Repository;
	@Autowired
	Role_Repository Jpa_Role_Repository;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private Email_Sender_Service emailSenderService;
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
		Dbo_User userToSave = new Dbo_User();

		userToSave.setFirstName(user.getFirstName());
		userToSave.setLastName(user.getLastName());
		userToSave.setPassword(passwordEncoder.encode(user.getPassword()));
		userToSave.setEmail(user.getEmail());
		userToSave.setActif(false);
		userToSave.setDate(user.getDate());

		return Jpa_User_Repository.save(userToSave);
	}

	@Override
	public String deleteById(Long id) {

		JSONObject jsonObject = new JSONObject();
		try {
			Jpa_User_Repository.deleteById(id);
			jsonObject.put("message", "User deleted successfully ");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	public void create_NewUser_After_OAuth2_Login_Success(String email, String name, Date birthdate,
			 Dbo_User_Provider provider , Date now , String sessionId ) {
		// TODO Auto-generated method stub
		Dbo_User UserOAuth2ToSave = new Dbo_User();
		UserOAuth2ToSave.setEmail(email);
		UserOAuth2ToSave.setFirstName(name);
		UserOAuth2ToSave.setLastName(name);
		UserOAuth2ToSave.setActif(true);
		UserOAuth2ToSave.setDate(birthdate);
		UserOAuth2ToSave.setCreatedTime(new Date());
		UserOAuth2ToSave.setLastLoggedIn(now);
		UserOAuth2ToSave.setPassword(passwordEncoder.encode(name));
		
		Set<Dbo_Role> roles = new HashSet<>();
		Dbo_Role userRole = Jpa_Role_Repository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		
		@SuppressWarnings("unused")
		Dbo_Role  Role_User = Jpa_Role_Repository.findByName("simpleUser");
		UserOAuth2ToSave.setRole(  roles);
        UserOAuth2ToSave.setDbo_User_Provider(provider);
        UserOAuth2ToSave.setSession_Id(sessionId);
        

		Jpa_User_Repository.save(UserOAuth2ToSave);
		Confirmation_Token_User confirmationToken = new Confirmation_Token_User(UserOAuth2ToSave);

		confirmationTokenRepository.save(confirmationToken);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("!! Secret Information !!");
		mailMessage.setFrom("ayoub.benzahra@esprit.tn");
		mailMessage.setText(" Dear Mr "+name+" "+
							"following an agreement created with the Google OAuth Client service, your password will be generated as follows [FirstName + LastName]");

		emailSenderService.sendEmail(mailMessage);

	}

	public void update_the_Existing_User_After_OAuth2_Login_Success(Dbo_User customer, String name,  
			Dbo_User_Provider providerSession ,Date now , String sessionId) {
		// TODO Auto-generated method stub
		 
		//customer.setDbo_User_Provider(providerSession);
		customer.setLastLoggedIn(now);
		customer.setSession_Id(sessionId);
		Jpa_User_Repository.save(customer);
	}

	@Override
	public Long FindIDUserByEmail(String email) {
 		return Jpa_User_Repository.FindIDUserByEmail(email);
	}

 
}

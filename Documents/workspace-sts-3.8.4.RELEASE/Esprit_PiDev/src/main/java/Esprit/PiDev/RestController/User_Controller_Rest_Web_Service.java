package Esprit.PiDev.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Confirmation_Token_User;
import Esprit.PiDev.Entity.Dbo_Role;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.Dbo_User_Provider;
import Esprit.PiDev.Entity.ERole;
import Esprit.PiDev.Entity.RequestApi.JwtResponse;
import Esprit.PiDev.Entity.RequestApi.MessageResponse;
import Esprit.PiDev.Entity.RequestApi.RequestLogin;
import Esprit.PiDev.Entity.RequestApi.SignupRequest;
import Esprit.PiDev.ExterneService.Custom_Oauth2_User;
import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.ConfirmationTokenRepository;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.Security.JwtUtils;
import Esprit.PiDev.Service.Email_Sender_Service;
import Esprit.PiDev.Service.Session_UserDetails;

@CrossOrigin("*")
@RestController

public class User_Controller_Rest_Web_Service {
 	@Autowired
	Interface_User_Service I_User_Service;
	@Autowired
	User_Repository Jpa_User_Repository;
	@Autowired
	Role_Repository Jpa_Role_Repository;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Email_Sender_Service emailSenderService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;

	private org.jboss.logging.Logger logger = LoggerFactory.logger(User_Controller_Rest_Web_Service.class);

	@RequestMapping("/LoggingTest")
	public String index() {
		logger.trace("A TRACE Message");
		logger.debug("A DEBUG Message");
		logger.info("An INFO Message");
		logger.warn("A WARN Message");
		logger.error("An ERROR Message");

		return "hi ! Check out the Logs to see the output...";
	}

	@RequestMapping("/securedPage")
	public String securedPage(Model model, Principal principal) {
		return "securedPage";
	}

	@RequestMapping("/")
	public String index(Model model, Principal principal) {
		return "index";
	}

	@PostMapping("/Sign-In")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody RequestLogin loginRequest ,HttpServletRequest request)
			throws UnsupportedEncodingException {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		logger.info("token is :" + jwt);
		Session_UserDetails userDetails = (Session_UserDetails) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		try{
		Jpa_User_Repository.updateUserTimeLoggedIn( userDetails.getId(), new Date());
		
		Jpa_User_Repository.updateUserSessionId(request.getSession().getId(), userDetails.getId());
		logger.info("Session reference is : "+request.getSession().getId() +" Id User : "+userDetails.getId());
		logger.info("Time_Logged_In has been updated for user with First name : "+userDetails.getUsername()  );
		}
		catch(Exception e)
		{
			logger.error("Something is wrong time logged in not updated "+e.getMessage());
		}

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));

	}

	@PostMapping("/logout")
	public ResponseEntity <?> logout(HttpSession session , Authentication auth) {
	session.invalidate();
	SecurityContextHolder.getContext().setAuthentication(auth);
	Session_UserDetails userDetails = (Session_UserDetails) auth.getPrincipal();

	Jpa_User_Repository.updateUserTimeLoggedOut(userDetails.getId(), new Date());
	logger.info("time logged out has been updated with user id : "+userDetails.getId());
	return ResponseEntity.ok(new MessageResponse("session logged out"));
	}
	
	@RequestMapping("/oauth2/authorization/google")
	public Object SignInWithGoogle(BindingResult bindingResult, HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Custom_Oauth2_User OauthUser = (Custom_Oauth2_User) authentication.getPrincipal();
		return OauthUser;

	}

	 

	@GetMapping("/retrieve-all-users")
	public List<Dbo_User> getUsers() {
		List<Dbo_User> list = I_User_Service.retrieveAllUsers();
		return list;
	}

	@GetMapping("/retrieve-user/{user-id}")
	public Optional<Dbo_User> retrieveUserById(@PathVariable("user-id") Long userId, HttpServletRequest request,
			HttpServletResponse response) throws UserPrincipalNotFoundException {
		if (I_User_Service.findById(userId) == null) {
			throw new UserPrincipalNotFoundException("Identify not matched or user not found");
		}
		return I_User_Service.findById(userId);
	}
 

	@DeleteMapping("/remove-user/{user-id}")
	public void removeUser(@PathVariable("user-id") Long userId) {
		Jpa_User_Repository.deleteById(userId);
	}

	@RequestMapping("/Sign-Up")
	public ResponseEntity<?> add_user(@Valid @RequestBody SignupRequest signUpRequest, BindingResult bindingResult) {

		if (Jpa_User_Repository.existsByFirstName(signUpRequest.getFirstName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (Jpa_User_Repository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Dbo_User dbo_User = new Dbo_User(signUpRequest.getEmail(), signUpRequest.getFirstName(),
				signUpRequest.getLastName(), false, signUpRequest.getDate(),
				passwordEncoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Dbo_Role> roles = new HashSet<>();

		if (strRoles == null) {
			Dbo_Role userRole = Jpa_Role_Repository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(MultiRole -> {
				switch (MultiRole) {
				case "admin":
					Dbo_Role adminRole = Jpa_Role_Repository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "dir":
					Dbo_Role dir = Jpa_Role_Repository.findByName(ERole.ROLE_DIRECTEUR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(dir);

					break;
				case "sousDir":
					Dbo_Role sousDir = Jpa_Role_Repository.findByName(ERole.ROLE_SOUDIRECTEUR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(sousDir);

					break;
				default:
					Dbo_Role user = Jpa_Role_Repository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(user);
				}
			});
		}

		dbo_User.setRole(roles);

		dbo_User.setDbo_User_Provider(Dbo_User_Provider.LOCAL);
		dbo_User.setCreatedTime(new Date());
		Jpa_User_Repository.save(dbo_User);

		/* Email Service and confirmation user account with token generated */
		Confirmation_Token_User confirmationToken = new Confirmation_Token_User(dbo_User);

		confirmationTokenRepository.save(confirmationToken);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(dbo_User.getEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setFrom("benzahra.ayoub15@gmail.com");
		mailMessage.setText("To confirm your account, please click here : "
				+ "http://localhost:8081/confirm-account?token=" + confirmationToken.getConfirmationToken());

		emailSenderService.sendEmail(mailMessage);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public Object confirmUserAccount(@RequestBody Confirmation_Token_User dbo_User, BindingResult bindingResult,
			@RequestParam("token") String confirmationToken) {
		// Map<String, Object> Errors = new HashMap<>();

		// Optional<Dbo_User> existingUserwithId =
		// Jpa_User_Repository.findByEmailIgnoreCase(dbo_User.getEmail());
		/*
		 * Optional<Confirmation_Token_User> UserToken =
		 * confirmationTokenRepository.findByUserIgnoreCase(dbo_User.getUser());
		 * if (!UserToken.isPresent() ) {
		 * 
		 * Errors.put("Errors : this user is not found please check again !",
		 * true);
		 * 
		 * for (FieldError fe : bindingResult.getFieldErrors()) {
		 * Errors.put(fe.getField(), fe.getDefaultMessage()); } return Errors; }
		 */
		Confirmation_Token_User token = confirmationTokenRepository.findByconfirmationToken(confirmationToken);
		if (token != null) {
			Dbo_User user = Jpa_User_Repository.findByEmail(token.getUser().getEmail());
			user.setActif(true);
			Jpa_User_Repository.save(user);
		}

		return token;
	}

	@Secured(value = { "ROLE_ADMIN" })
	@PutMapping("/updateUser/{user-id}")
	public Object updateUser(@RequestBody Dbo_User dbo_User, @PathVariable("user-id") Long id,
			BindingResult bindingResult) {
		Dbo_User userToUpdate = Jpa_User_Repository.findById(id).orElse(null);

		String lastName = dbo_User.getLastName();
		userToUpdate.setLastName(lastName);
		String firstname = dbo_User.getFirstName();
		userToUpdate.setFirstName(firstname);
		Date date = dbo_User.getDate();
		userToUpdate.setDate(date);
		boolean actif = dbo_User.isActif();
		userToUpdate.setActif(actif);
		if (dbo_User.getRole() == null) {
			userToUpdate.setRole(userToUpdate.getRole());
		}
		Map<String, Object> Errors = new HashMap<>();

		// Optional<Dbo_User> existingUserWithEmail
		// =Jpa_User_Repository.findByEmailIgnoreCase(dbo_User.getEmail());
		// Restricted without Confirmation

		Optional<Dbo_User> existingUserWithFirstName = Jpa_User_Repository
				.findByFirstNameIgnoreCase(dbo_User.getFirstName());
		Optional<Dbo_User> existingUserWithLastName = Jpa_User_Repository
				.findByLastNameIgnoreCase(dbo_User.getLastName());

		if (existingUserWithFirstName.isPresent()) {

			Errors.put("Errors : this First Name is already exist please choose another one !", true);
			for (FieldError fe : bindingResult.getFieldErrors()) {
				Errors.put(fe.getField(), fe.getDefaultMessage());
			}
			return Errors;
		} else if (existingUserWithLastName.isPresent()) {

			Errors.put("Errors : this Last Name is already exist please choose another one !", true);
			for (FieldError fe : bindingResult.getFieldErrors()) {
				Errors.put(fe.getField(), fe.getDefaultMessage());
			}
			return Errors;
		}

		return Jpa_User_Repository.save(userToUpdate);

	}

	@PreAuthorize("hasAuthority('ROLE_simpleUser') or hasAuthority('ROLE_sousDirecteur') or hasAuthority('ROLE_DIRECTEUR') or hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/getLogedUser")
	public Map<String, Object> getLogedUser(HttpServletRequest httpServletRequest) {
		HttpSession httpSession = httpServletRequest.getSession();
		SecurityContext context = (SecurityContext) httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		String username = context.getAuthentication().getName();
		List<String> roles = new ArrayList<>();
		for (GrantedAuthority ga : context.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		Map<String, Object> params = new HashMap<>();
		params.put("Email", username);
		params.put("role of " + username, roles);
		return params;
	}

}

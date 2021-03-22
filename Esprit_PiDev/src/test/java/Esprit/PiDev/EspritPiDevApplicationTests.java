package Esprit.PiDev;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nimbusds.jose.shaded.json.parser.ParseException;

import Esprit.PiDev.InterfaceService.Interface_User_Service;
import Esprit.PiDev.Repository.Role_Repository;
import Esprit.PiDev.Repository.User_Repository;
import Esprit.PiDev.Service.User_Role_Service;
import Esprit.PiDev.Service.User_Service;

@SpringBootTest
class EspritPiDevApplicationTests {
/*
	@Test
	void contextLoads() {

	}

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

	@Test
	public void testAdd() throws ParseException, java.text.ParseException {
		System.out.println("****************");
		ur1.FindIDUserByEmail("hoss@esprit.tn");
		System.out.println(ur1.FindIDUserByEmail("hoss@esprit.tn"));
		System.out.println("***************");
	}

	/*
	 * @Test public void testAdd1() throws ParseException,
	 * java.text.ParseException { SimpleDateFormat dateFormat = new
	 * SimpleDateFormat("yyyy-MM-dd"); Date d = dateFormat.parse("1995-03-05");
	 * 
	 * Dbo_Role role = new Dbo_Role("admin");
	 * 
	 * //Dbo_Role dbo_Role =role_Repository.save(role);
	 * System.out.println("********  testAdd  addRole*************"); Dbo_Role
	 * dbo_Role= ur.addRole(role);
	 * System.out.println("********  testAdd1*************"); Dbo_User user =
	 * new Dbo_User(); user.setFirstName("Ayoub");
	 * user.setLastName("Ben zahra"); user.setEmail("ayoub.benzahra@esprit.tn");
	 * user.setDate(d); user.setPassword("123456"); user.setActif(true); //
	 * user.setRole(roleService.findById(1L).get()); // user.setRole(dbo_Role);
	 * user.setRole(dbo_Role);
	 * 
	 * userService.saveOrUpdate(user);
	 * System.out.println("***"+user.toString());
	 * 
	 * System.out.println("******** testAdd1*************"); }
	 */
	
	

}
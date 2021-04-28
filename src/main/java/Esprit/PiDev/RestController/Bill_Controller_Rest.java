package Esprit.PiDev.RestController;

import java.io.FileNotFoundException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Bill;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Exception.API_Request_Exception_UNAUTHORIZED_STATUS_403;
import Esprit.PiDev.InterfaceService.Interface_Bill_Service;
import Esprit.PiDev.Service.Bill_Report_Service;
import Esprit.PiDev.dto.BillDto;
import net.sf.jasperreports.engine.JRException;

@RestController
public class Bill_Controller_Rest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	Interface_Bill_Service billservice;
	@Autowired
	Bill_Report_Service billReportservice;

	@RequestMapping("/addBill/{user-id}/{garden-id}")
	@ResponseBody
	public void ajouterBill(@RequestBody Bill bill, @PathVariable("user-id") Long userid,
			@PathVariable("garden-id") Long gardenid) {

		billservice.Add_Bill(bill, userid, gardenid);

		// return ResponseEntity.ok(new MessageResponse(" bill is Saved"));

	}

	@PutMapping("/discountEnfantBill/{user-id}/{garden-id}")
	@ResponseBody
	public ResponseEntity<?> discountEnfantBill(Authentication authentication, @PathVariable("user-id") Long userid,
			@PathVariable("garden-id") Long gardenid) {
		if (!(authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")))) {
			throw new API_Request_Exception_UNAUTHORIZED_STATUS_403(
					authentication.getName().toUpperCase() + " IS UNAUTHORIZED CONTENT WITH THIS AUTHORITY !!! ");
		} else {
			billservice.discountEnfant(userid, gardenid);
			return ResponseEntity.ok(new MessageResponse(" discount is Saved"));
		}

	}

	@PutMapping("/updateBill/{bill-id}")
	public void updateForum(Authentication authentication, @RequestBody Bill bill,
			@PathVariable("bill-id") Long billid) {
		// SecurityContextHolder.getContext().setAuthentication(authentication);
		// Session_UserDetails userDetails = (Session_UserDetails)
		// authentication.getPrincipal();
		billservice.Update_Bill(bill, billid);
	}

	@GetMapping("/report/{format}")
	@ResponseBody
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return billReportservice.exportReport(format);
	}

	@GetMapping("/reportForUserInKindergarten/{format}/{iduser}/{idkinder}")
	@ResponseBody
	public String exportReportForUserInKinder(@PathVariable String format, @PathVariable("iduser") int iduser,
			@PathVariable("idkinder") int idkinder) throws FileNotFoundException, JRException {
		return billReportservice.exportReportForUserInKinder(format, iduser, idkinder);
	}

	@GetMapping("/getAllBills")
	public List<Bill> getAllBills(Authentication auth) {
		return billservice.Retrieve_All_bills();

	}
	
	@GetMapping("/bill")
	public List<BillDto> findAllBills(Authentication auth) {
		return billservice.findAllBills();
	}

	@GetMapping("/retrieve-bill/{bill-id}")
	@ResponseBody
	public Bill retrieveBill(@PathVariable("bill-id") Long billId) {

		// SecurityContextHolder.getContext().setAuthentication(auth);
		// Session_UserDetails userDetails = (Session_UserDetails)
		// auth.getPrincipal();
		return billservice.findBill(billId);

	}

	@DeleteMapping("/remove-Bill/{bill-id}")
	public void removeBill(@PathVariable("bill-id") Long billId) {
		billservice.deleteBill(billId);
	}

}

/*
 * 
 * 
 * 
 * 
 * 
 * @GetMapping("/retrieveBill/{bill-id}//{user-id}") public ResponseEntity<?>
 * retrieveForum(Authentication authentication,@PathVariable("bill-id") Long
 * billid, @PathVariable("user-id") Long userid) { if (
 * !(authentication.getAuthorities().stream().anyMatch(authority ->
 * authority.getAuthority().equals("ROLE_ADMIN")))) { throw new
 * API_Request_Exception_UNAUTHORIZED_STATUS_403(authentication.getName().
 * toUpperCase() + " IS UNAUTHORIZED CONTENT WITH THIS AUTHORITY !!! "); } else
 * { billservice.Retrieve_Bill(billid, userid); return ResponseEntity.ok(new
 * MessageResponse("Saved")); } }
 * 
 * 
 * /*
 * 
 * @DeleteMapping("/removeForumComment/{comment-id}") public void
 * removeForum(@PathVariable("comment-id") Long commentId){
 * fs.deleteForum(commentId); }
 * 
 * 
 * 
 * 
 * /*
 * 
 * @GetMapping("/getAllForumsComment")
 * 
 * @ResponseBody public List<ForumComment> getAllForums() { return
 * fs.Retrieve_All_Forum(); }
 * 
 * 
 * @DeleteMapping("/removeForumComment/{comment-id}") public void
 * removeForum(@PathVariable("comment-id") Long commentId){
 * fs.deleteForum(commentId); }
 * 
 * @PutMapping("update-forumComment") public void updateForum(@RequestBody
 * ForumComment f){ fs.Update_Forum(f);
 * 
 * }
 * 
 * @GetMapping("/retrieve-forumComment/{comment-id}") public ForumComment
 * retrieveForum(@PathVariable("comment-id") Long commentId) { return
 * fs.retrieveForum(commentId); }
 */

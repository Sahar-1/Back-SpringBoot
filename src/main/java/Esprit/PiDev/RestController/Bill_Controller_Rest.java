package Esprit.PiDev.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Esprit.PiDev.Entity.Bill;
import Esprit.PiDev.Entity.RequestApiForm.MessageResponse;
import Esprit.PiDev.Exception.API_Request_Exception_UNAUTHORIZED_STATUS_403;
import Esprit.PiDev.InterfaceService.Interface_Bill_Service;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


@RestController
public class Bill_Controller_Rest {
	
	
	 @Autowired
	private DataSource dataSource;
	

	@Autowired
Interface_Bill_Service billservice;
	
	
	@RequestMapping("/generate")
	public void imprimir(@RequestParam Map<String, Object> parametros, HttpServletResponse response) throws JRException, SQLException, IOException {
		
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		
		// Pega o arquivo .jasper localizado em resources
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/livros.jasper");
		
		// Cria o objeto JaperReport com o Stream do arquivo jasper
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		// Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros , dataSource.getConnection());

		// Configura a respota para o tipo PDF
		response.setContentType("application/pdf");
		// Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
		// para fazer download do relatório troque 'inline' por 'attachment'
		response.setHeader("Content-Disposition", "inline; filename=livros.pdf");

		// Faz a exportação do relatório para o HttpServletResponse
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	
	@RequestMapping("/addBill/{user-id}")
	@ResponseBody
	public ResponseEntity<?> ajouterBill( Authentication authentication,@RequestBody Bill bill,  @PathVariable("user-id") Long userid) {
	if ( !(authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")))) {
        throw new API_Request_Exception_UNAUTHORIZED_STATUS_403(authentication.getName().toUpperCase() + " IS UNAUTHORIZED CONTENT WITH THIS AUTHORITY !!! ");
    } else {
        billservice.Add_Bill(bill,userid);
        return ResponseEntity.ok(new MessageResponse(" bill is Saved"));
    }	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	@PutMapping("/updateBill")
	public ResponseEntity<?> updateForum(Authentication authentication,@RequestBody Bill bill,  @PathVariable("user-id") Long userid){
		if ( !(authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")))) {
	        throw new API_Request_Exception_UNAUTHORIZED_STATUS_403(authentication.getName().toUpperCase() + " IS UNAUTHORIZED CONTENT WITH THIS AUTHORITY !!! ");
	    } else {
	        billservice.Update_Bill(bill, userid);
	        return ResponseEntity.ok(new MessageResponse("Saved"));
	    }	
	}
	
	
	
	@GetMapping("/retrieveBill/{bill-id}//{user-id}")
	public ResponseEntity<?> retrieveForum(Authentication authentication,@PathVariable("bill-id") Long billid,  @PathVariable("user-id") Long userid) {
		if ( !(authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")))) {
	        throw new API_Request_Exception_UNAUTHORIZED_STATUS_403(authentication.getName().toUpperCase() + " IS UNAUTHORIZED CONTENT WITH THIS AUTHORITY !!! ");
	    } else {
	        billservice.Retrieve_Bill(billid, userid);
	        return ResponseEntity.ok(new MessageResponse("Saved"));
	    }	
	}
	
	
	/*
	@DeleteMapping("/removeForumComment/{comment-id}")
	public void removeForum(@PathVariable("comment-id") Long commentId){
		fs.deleteForum(commentId);
	}
	  
	  
	  
	
	/*
	@GetMapping("/getAllForumsComment")
	  @ResponseBody
	  public List<ForumComment> getAllForums() {
		 return fs.Retrieve_All_Forum();
	  }
	
	
	@DeleteMapping("/removeForumComment/{comment-id}")
	public void removeForum(@PathVariable("comment-id") Long commentId){
		fs.deleteForum(commentId);
	}
	
	@PutMapping("update-forumComment")
	public void updateForum(@RequestBody ForumComment f){
		fs.Update_Forum(f);
		
	}
	
	@GetMapping("/retrieve-forumComment/{comment-id}")
	public ForumComment retrieveForum(@PathVariable("comment-id") Long commentId) {
	return fs.retrieveForum(commentId);
	}
*/


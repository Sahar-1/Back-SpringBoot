package Esprit.PiDev.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import Esprit.PiDev.Entity.Bill;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Repository.Bill_Repository;
import Esprit.PiDev.Repository.Garden_Repository;
import Esprit.PiDev.Repository.User_Repository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class Bill_Report_Service {
	private String bill;
	String name_user;
	private  static final  long TOTAL_AMOUNT  = 7000;
	@Autowired
	Bill_Repository Bill_rep;
	@Autowired
	User_Repository usRep;
	@Autowired
	Garden_Repository garRep;
	
	
	
	public String exportReport(String reportFormat ) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\ons\\git\\Esprit-PiDev-4Sae6"  ;
        List<Bill> bill = (List<Bill>) Bill_rep.findAll();
        for (Bill b : bill){
        	
        	b.setGardenpk(b.getGarden().getName());
        	b.setUserpk(b.getUser().getFirstName() + " "+ b.getUser().getLastName());
        	
        }


        //load file and compile it
        File file = ResourceUtils.getFile("classpath:bill1.xml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bill);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
       
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\bill1.pdf");
        }

        return "report generated in path : " + path;
    }

	
	//bill by garden and user
	
	
	public String exportReportForUserInKinder(String reportFormat  ,long iduser , long idkinder ) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\ons\\git\\Esprit-PiDev-4Sae6" ;
   
       
      
		List<Bill> bill = (List<Bill>) Bill_rep.bill_byparent_kinder(iduser, idkinder);
		Dbo_User this_User = usRep.findById(iduser).orElseThrow( 
    			()-> new RuntimeException());
    	this.bill = this_User.getFullName();
    	this.name_user=this_User.getFullName();
        for (Bill b : bill){
        	Long amout_rest_to_pay = (long) (TOTAL_AMOUNT - b.getamount()); 
        	Long amount_already_payed = (long) b.getamount();
        	b.setAmount_payed(String.valueOf(amount_already_payed));
        	b.setAmount_not_payed(String.valueOf(amout_rest_to_pay));
        	
        	b.setGardenpk(b.getGarden().getName());
        	b.setUserpk(b.getUser().getFirstName() + " "+ b.getUser().getLastName());
        }
        
       
        
      //load file and compile it
        File file = ResourceUtils.getFile("classpath:userBill.xml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bill);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
       Date date = new Date();
       Format formatter = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
        if (reportFormat.equalsIgnoreCase("pdf")) { 
        	
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\bill_User"+"_"+this.name_user+"_"+ formatter.format(date) +".pdf");
        }

        return "report generated in path : " + path;
    }
	

	}
	




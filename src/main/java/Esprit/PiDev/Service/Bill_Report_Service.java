package Esprit.PiDev.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import Esprit.PiDev.Entity.Bill;
import Esprit.PiDev.Repository.Bill_Repository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class Bill_Report_Service {
	
	
	@Autowired
	Bill_Repository Bill_rep;
	
	
	
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

	
	
	
	// bill by garden and user
	
	
	public String exportReportForUserInKinder(String reportFormat  ,int iduser , int idkinder ) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\ons\\git\\Esprit-PiDev-4Sae6" ;
        
       
        List<Bill> bill = (List<Bill>) Bill_rep.bill_byparent(iduser, idkinder);
        for (Bill b : bill){
        	
        	b.setGardenpk(b.getGarden().getName());
        	b.setUserpk(b.getUser().getFirstName() + " "+ b.getUser().getLastName());
        }
        
       
        
      //load file and compile it
        File file = ResourceUtils.getFile("classpath:billUser.xml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bill);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
       
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\bill_User.pdf");
        }

        return "report generated in path : " + path;
    }
	

	}
	




package Esprit.PiDev.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Appointment;

@Repository
public interface Appointment_Repository extends CrudRepository<Appointment, Integer> {
	
	@Query("select a  from Appointment a WHERE YEAR(a.date) =:year  and a.status =1 ")
	List<Appointment> find_appointment_year(@Param("year") int year);
	
	@Query("SELECT m FROM Appointment m WHERE m.description LIKE %:search%  OR m.date LIKE %:search% OR m.beginhour LIKE %:search% ")
	List<Appointment> searchappointment(@Param("search") String search);
	
	@Query("select a  from Appointment a  join a.user  u WHERE  u.id =:parent_id")
	List<Appointment> find_appointment_byparent(@Param("parent_id") long parent_id);
	
	@Query("select a  from Appointment a  join a.garden g WHERE  a.date =:date and g.id =:garden_id ")
	List<Appointment> find_date_appointment_bygarden(@Param("date") Date date,@Param("garden_id") int garden_id);

}

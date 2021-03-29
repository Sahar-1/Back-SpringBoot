package Esprit.PiDev.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Bill;
import Esprit.PiDev.Entity.Garden;
@Repository
public interface Bill_Repository extends CrudRepository<Bill, Long>{
	
	@Query("SELECT  b  from Bill b join b.user u join b.garden g where u.id=:id_parent and g.id=:garden ")
	Bill bill_byparent(@Param("id_parent") long id_parent,@Param("garden") long garden);
	

	@Query("SELECT  b  from Bill b join b.user u join b.garden g where u.id=:id_parent and g.id=:garden ")
	public List<Bill> bill_byparent_kinder(@Param("id_parent") long id_parent,@Param("garden") long garden);

	@Modifying
	@Transactional
	@Query("UPDATE Bill set amount=:amount where user_user_id=:user_id")
	public void setFacture_Amount(@Param("user_id") long user_id ,@Param("amount") double amount);
	
	//@Query("SELECT  g  from Bill b join b.garden g where g.id=:garden")
	//Garden bill_bygarden(@Param("garden") long garden);
/*	@Query("SELECT count (*)  from Dbo_User c where c.parent_id.id=:user AND c.garden.id=:kindergarten")
	public long getNumberOfChildForUserInKinderJPQL(@Param("user") int user,@Param("kindergarten") int kindergarten);
/*
 * 
 */
}

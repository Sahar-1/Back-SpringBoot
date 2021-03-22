package Esprit.PiDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Garden;

@Repository
public interface Garden_Repository  extends CrudRepository<Garden, Long>{
	
	Garden findByDescription(String description);
	Garden findByPhone(int phone);
	Garden findByName(String name);
	List<Garden> findGardenByNameAndPhone(String name,int phone);
	@Query(value = "SELECT DISTINCT u.parent_id FROM Dbo_User u  join u.garden g where g.id =:id and u.parent_id IS NOT NULL")
	public List<Long> select_parent_by_Garden(@Param("id") Long id);
	
	//@Query(value = "SELECT  u FROM Dbo_User u  join u.garden g where g.id =:garden_id and u.parent_id =: id1")
	//public List<Dbo_User> select_enfant_parent_by_Garden(@Param("garden_id") int id,@Param("id1") long id1);
	
}

package Esprit.PiDev.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Activity;
@Repository
public interface Activity_Repository extends CrudRepository<Activity, Integer> {

	
//	@Query(value = "SELECT  u FROM Activity u  join u.garden g where g.id = :garden_id and u.title like %:search% or y.description like %:search% or u.date like %:search%")
	//public List<Activity> findSearch(@Param("search") String search,@Param("garden_id") int garden_id);
	
	List<Activity> findByTitle(String title);
}

package Esprit.PiDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Post;
@Repository
public interface Post_Repository extends  CrudRepository<Post,Long>{
	
	@Query("SELECT m FROM Post m WHERE m.title LIKE %:search%  OR m.types LIKE %:search% OR m.subject LIKE %:search% ")
	List<Post> ChercherPost(@Param("search") String search);

}

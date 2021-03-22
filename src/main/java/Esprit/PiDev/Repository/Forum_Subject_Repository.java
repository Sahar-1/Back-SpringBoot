package Esprit.PiDev.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Esprit.PiDev.Entity.ForumSubject;

public interface Forum_Subject_Repository extends CrudRepository<ForumSubject, Long>{


	
	@Query("select count(*) from ForumSubject f")
	public int countForumsSubject();
	
	
}

package Esprit.PiDev.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.ForumComment;
@Repository
public interface Forum_Comment_Repository extends JpaRepository<ForumComment,Long > {
	@Query("select count(*) from ForumComment f")
	public int countForumsComments();
	
	
	
	
	
	
	
}






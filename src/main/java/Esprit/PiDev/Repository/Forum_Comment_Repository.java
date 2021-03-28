package Esprit.PiDev.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.ForumComment;
@Repository
public interface Forum_Comment_Repository extends JpaRepository<ForumComment,Long > {
	@Query("select count(*) from ForumComment f")
	public int countForumsComments();
	
	
	@Query("select count(a) from ForumComment a join a.forumSubject f where f.id=:IDforumSubject")
	int  countComment( @Param("IDforumSubject") long IDforumSubject);
	
	@Query("select count(a) from ForumComment a join a.forumSubject f where f.id=:IDforumSubject and f.question LIKE %:question% ")
	int  countsubjectquetions( @Param("IDforumSubject") long IDforumSubject,@Param("question") String question);
	
	
	
	
	
	
}






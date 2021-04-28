package Esprit.PiDev.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.ForumComment;
import Esprit.PiDev.Entity.ForumSubject;
@Repository
public interface Forum_Comment_Repository extends JpaRepository<ForumComment,Long > {
	@Query("select count(*) from ForumComment f")
	public int countForumsComments();
	
	
	@Query("select count(a) from ForumComment a join a.forumSubject f where f.id=:IDforumSubject")
	int  countComment( @Param("IDforumSubject") long IDforumSubject);
	
	@Query("select count(a) from ForumComment a join a.forumSubject f where f.id=:IDforumSubject and f.question LIKE %:question% ")
	int  countsubjectquetions( @Param("IDforumSubject") long IDforumSubject,@Param("question") String question);
	
	
	@Query("SELECT com from ForumComment com WHERE com.forumSubject =:IDforumSubject")
	public List<ForumComment>  findCommentBySubject(@Param("IDforumSubject") ForumSubject IDforumSubject);
	
	/*
	@Query("Select * from forumSubject r , ForumComment c where r.id=c.IDforumSubject orderBy c.question Limit 1")
	int  countmaxCommentbySubject( @Param("IDforumSubject") long IDforumSubject);
	*/
	
	
}






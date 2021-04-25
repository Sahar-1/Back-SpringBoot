package Esprit.PiDev.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Reaction;

@Repository
public interface Reaction_Repository extends CrudRepository<Reaction, Long>{
	
	@Query("select  count(*) from Reaction ")
	int  countreact(); 
	
	
	@Query("select  count(r) from Reaction r join r.post p where p.id=:id_post and r.liked =:like" )
	int  countreacttrue( @Param("id_post") Long id_post,@Param("like") boolean like);
	@Query("select  count(r) from Reaction r join r.post p where p.id=:id_post and r.liked =:dislike" )
	int  countreactfalse( @Param("id_post") Long id_post,@Param("dislike") boolean dislike);

}

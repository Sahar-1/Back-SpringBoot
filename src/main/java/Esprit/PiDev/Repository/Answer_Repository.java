package Esprit.PiDev.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Answer_Satisfaction;
import Esprit.PiDev.Entity.Review;

@Repository
public interface Answer_Repository extends CrudRepository<Answer_Satisfaction, Long>{

	
	@Query(value="select  count(a) from answer_satisfaction a join a.question q  where    q.id=:id_question", nativeQuery = true)
	int  countasnwer( @Param("id_question") int id_question);
	
	@Query(value="select  count(a) from answer_satisfaction a join a.question q  where    q.id=:id_question and a.content =:GOOD", nativeQuery = true )
	int  countasnwergood( @Param("id_question") int id_question);
	@Query(value="select  count(a) from answer_satisfaction a join a.question q  where    q.id=:id_question and a.content =:BAD", nativeQuery = true )
	int  countasnwerbad( @Param("id_question") int id_question);
	@Query(value="select  count(a) from answer_satisfaction a join a.question q  where    q.id=:id_question and a.content =:MEDIUM", nativeQuery = true )
	int  countasnwermedium( @Param("id_question") int id_question);
	
}

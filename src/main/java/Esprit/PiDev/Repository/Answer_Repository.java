package Esprit.PiDev.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Answer_Satisfaction;
import Esprit.PiDev.Entity.Review;

@Repository
public interface Answer_Repository extends CrudRepository<Answer_Satisfaction, Long>{
	@Query("select  count(A) from Answer_Satisfaction A join A.question q  where  q.id=:id_question")
	int  countasnwer(@Param("id_question") Long id_question);
	@Query("select  count(A) from Answer_Satisfaction A join A.question q  where    q.id=:id_question and A.review LIKE CONCAT('%',:review,'%')" )
	int  countasnwergood(@Param("id_question") Long id_question ,@Param("review") Review review);
	@Query("select  count(A) from Answer_Satisfaction A join A.question q  where    q.id=:id_question and A.review LIKE CONCAT('%',:review,'%')" )
	int  countasnwerbad(@Param("id_question") Long id_question,@Param("review") Review review);
	@Query("select  count(A) from Answer_Satisfaction A join A.question q  where    q.id=:id_question and A.review LIKE CONCAT('%',:review,'%')" )
	int  countasnwermedium(@Param("id_question") Long id_question,@Param("review") Review review);	
}

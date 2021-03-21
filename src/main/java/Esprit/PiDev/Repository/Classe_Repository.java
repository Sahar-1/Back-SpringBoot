package Esprit.PiDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Classe;

@Repository
public interface Classe_Repository extends CrudRepository<Classe,Integer> {

	
	
	@Query("select  c from Classe c where c.capacite > 0   ")
	 List<Classe> findCapacite();
	
	@Query("select  c from Classe c join c.garden g  where  c.capacite > 0 and  g.id=:id_garden")
	List<Classe> findCapacitebygarden( @Param("id_garden") Long id_garden);
	
	@Query("select  max(c.compteur)  from Classe c join c.garden g  where g.id=:id_garden  ")
	int max_compteur_garden(@Param("id_garden") Long id_garden);
	
	@Query("select  count(c) from Classe c join c.garden g  where g.id=:id_garden  ")
	Long existe_garden_id(@Param("id_garden") Long id_garden);
	
	
	
	
	
}

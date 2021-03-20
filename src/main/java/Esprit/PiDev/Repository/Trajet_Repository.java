package Esprit.PiDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Classe;
import Esprit.PiDev.Entity.Dbo_User;
import Esprit.PiDev.Entity.Trajet;

@Repository
public interface Trajet_Repository  extends CrudRepository<Trajet, Integer> {

	@Query("select  t from Trajet t  join  t.dbo_User u   where u.id=:id_user  ")
	List<Trajet> select_trajet_bychauffeur(@Param("id_user") long id_user);

	
	@Query("select  u from Dbo_User u  join  u.classe c where c.id=:classe_id  ")
	List<Dbo_User> select_classes_bytrajet(@Param("classe_id") int classe_id);
}

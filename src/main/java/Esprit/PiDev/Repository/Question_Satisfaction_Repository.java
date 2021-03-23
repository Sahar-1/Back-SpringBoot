package Esprit.PiDev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Question_Satisfaction;
@Repository
public interface Question_Satisfaction_Repository extends JpaRepository<Question_Satisfaction, Long>{

	
//	Question_Satisfaction findByQuestion_Sat(String Question_Sat);

}

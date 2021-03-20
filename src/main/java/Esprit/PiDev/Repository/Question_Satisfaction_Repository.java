package Esprit.PiDev.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Question_Satisfaction;
@Repository
public interface Question_Satisfaction_Repository extends JpaRepository<Question_Satisfaction, Long>{
}

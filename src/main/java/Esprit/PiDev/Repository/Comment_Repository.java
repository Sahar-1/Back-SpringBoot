package Esprit.PiDev.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Comment;

@Repository
public interface Comment_Repository extends CrudRepository<Comment, Long> {

}

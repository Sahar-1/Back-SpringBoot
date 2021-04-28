package Esprit.PiDev.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Vote;
@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

}

package Esprit.PiDev.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esprit.PiDev.Entity.Dbo_Role;
import Esprit.PiDev.Entity.ERole;

@Repository
public interface Role_Repository extends JpaRepository<Dbo_Role, Long> {

	Optional<Dbo_Role> findByName(ERole name);

	Dbo_Role findByName(String nameRole);
}

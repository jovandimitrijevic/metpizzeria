package rs.ac.metropolitan.it355.pz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.metropolitan.it355.pz.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}

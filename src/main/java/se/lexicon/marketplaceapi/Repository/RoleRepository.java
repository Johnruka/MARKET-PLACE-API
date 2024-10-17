package se.lexicon.marketplaceapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.marketplaceapi.domain.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}

package se.lexicon.marketplaceapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.marketplaceapi.domain.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}

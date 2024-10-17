package se.lexicon.marketplaceapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.marketplaceapi.domain.entity.Advertisement;

import java.util.List;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, String> {

    List<Advertisement> findByTitleContains(String title);

    List<Advertisement> findByPerson_Id(Long personId);
}

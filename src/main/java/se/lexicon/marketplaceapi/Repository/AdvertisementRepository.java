package se.lexicon.marketplaceapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.marketplaceapi.domain.entity.Advertisement;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, String> {

    List<Advertisement> findByTitleContains(String title);

    List<Advertisement> findByPerson_Id(Long personId);
}

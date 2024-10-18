package se.lexicon.marketplaceapi.Service;

import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOForm;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOView;

import java.util.List;

public interface AdvertisementService {

    AdvertisementDTOView create(AdvertisementDTOForm advertisementDTOForm);

    AdvertisementDTOView findById(String advertisementId);

    AdvertisementDTOView findById(Long advertisementId);

    void update(AdvertisementDTOForm advertisementDTOForm);

    void delete(String id);

    List<AdvertisementDTOView> findAdvertisementsByPersonId(Long personId);
}

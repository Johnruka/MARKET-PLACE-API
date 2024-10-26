package se.lexicon.marketplaceapi.Service;

import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOForm;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOView;
import se.lexicon.marketplaceapi.domain.entity.Advertisement;

import java.time.LocalDateTime;
import java.util.List;

public interface AdvertisementService {

    AdvertisementDTOView create(AdvertisementDTOForm advertisementDTOForm);

    AdvertisementDTOView findById(String advertisementId);

    void update(AdvertisementDTOForm advertisementDTOForm);

    void delete(String id);

    List<Object> findAdvertisementsByPersonId(Long personId);

    Object convertToAdvertisementDTOView(Advertisement advertisement);

    AdvertisementDTOView display(AdvertisementDTOForm advertisementDTOForm);

    LocalDateTime getCreateDateTime(AdvertisementDTOForm advertisementDTOForm);
}

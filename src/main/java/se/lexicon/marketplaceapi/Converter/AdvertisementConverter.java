package se.lexicon.marketplaceapi.Converter;

import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOForm;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOView;
import se.lexicon.marketplaceapi.domain.entity.Advertisement;

public interface AdvertisementConverter {

    AdvertisementDTOView toAdvertisementDTO(AdvertisementDTOView entity);

    Advertisement toEntity(AdvertisementDTOForm dto);
}

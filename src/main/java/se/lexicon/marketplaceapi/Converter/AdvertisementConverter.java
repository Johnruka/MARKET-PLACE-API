package se.lexicon.marketplaceapi.Converter;


import org.springframework.stereotype.Component;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOForm;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOView;
import se.lexicon.marketplaceapi.domain.entity.Advertisement;

@Component
public interface AdvertisementConverter {

    AdvertisementDTOView toAdvertisementDTO(AdvertisementDTOView entity);

    Advertisement toEntity(AdvertisementDTOForm dto);
}

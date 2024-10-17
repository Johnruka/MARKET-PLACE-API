package se.lexicon.marketplaceapi.Converter.Impl;

import org.springframework.stereotype.Component;
import se.lexicon.marketplaceapi.Converter.AdvertisementConverter;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOForm;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOView;
import se.lexicon.marketplaceapi.domain.entity.Advertisement;

@Component
public class AdvertisementConverterImpl implements AdvertisementConverter {


    @Override
    public AdvertisementDTOView toAdvertisementDTO(AdvertisementDTOView entity) {
    return AdvertisementDTOView.builder()
            .id(entity.getId())
            .title(entity.getTitle())
            .content(entity.getContent())
            .attachments(entity.getAttachments())
            .createDateTime(entity.getCreateDateTime())
            .expiryDateTime(entity.getExpiryDateTime())
            .build();
    }

    @Override
    public Advertisement toEntity(AdvertisementDTOForm dto) {
      return Advertisement.builder()
              .id(dto.toString())
              .title(dto.getTitle())
              .content(dto.getContent())
              .attachments(dto.getAttachments())
              .createDateTime(dto.getCreateDateTime().atStartOfDay())
              .expiryDateTime(dto.getExpiryDateTime().atStartOfDay()).build();

    }
}

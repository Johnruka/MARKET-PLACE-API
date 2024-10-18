package se.lexicon.marketplaceapi.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.marketplaceapi.Exception.DataNotFoundException;
import se.lexicon.marketplaceapi.Repository.AdvertisementRepository;
import se.lexicon.marketplaceapi.Repository.PersonRepository;
import se.lexicon.marketplaceapi.Service.AdvertisementService;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOForm;
import se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOView;
import se.lexicon.marketplaceapi.domain.dto.PersonDTOView;
import se.lexicon.marketplaceapi.domain.entity.Advertisement;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final PersonRepository personRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, PersonRepository personRepository) {
        this.advertisementRepository = advertisementRepository;
        this.personRepository = personRepository;
    }

    @Override
    public AdvertisementDTOView create(AdvertisementDTOForm advertisementDTOForm) {
        Advertisement advertisement = Advertisement.builder()
                .title(advertisementDTOForm.getTitle())
                .content(advertisementDTOForm.getContent())
                .attachments(advertisementDTOForm.getAttachments())
                .createDateTime(advertisementDTOForm.getCreateDateTime().atStartOfDay())
                .expiryDateTime(advertisementDTOForm.getExpiryDateTime().atStartOfDay())
                .build();
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);

        PersonDTOView builtPersonView = PersonDTOView.builder()
                .id(savedAdvertisement.getPerson().getId())
                .name(savedAdvertisement.getPerson().getName())
                .build();

        return AdvertisementDTOView.builder()
                .id(Long.valueOf(savedAdvertisement.getId()))
                .title(savedAdvertisement.getTitle())
                .content(savedAdvertisement.getContent())
                .attachments(savedAdvertisement.getAttachments())
                .createDateTime(LocalDate.from(savedAdvertisement.getCreateDateTime()))
                .expiryDateTime(LocalDate.from(savedAdvertisement.getExpiryDateTime()))
                .build();



    }

    @Override
    public AdvertisementDTOView findById(Long advertisementId) {
        return null;
    }

    @Override
    public void update(AdvertisementDTOForm advertisementDTOForm) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<AdvertisementDTOView> findAdvertisementsByPersonId(Long personId) {
        return List.of();
    }
}





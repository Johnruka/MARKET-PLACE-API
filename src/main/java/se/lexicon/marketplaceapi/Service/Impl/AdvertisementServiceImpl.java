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
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

        // Creating a new advertisement entity using the DTO
        Advertisement advertisement = Advertisement.builder()
                .title(advertisementDTOForm.getTitle())
                .content(advertisementDTOForm.getContent())
                .attachments(advertisementDTOForm.getAttachments())
                .createDateTime(advertisementDTOForm.getCreateDateTime().atStartOfDay())
                .expiryDateTime(advertisementDTOForm.getExpiryDateTime().atStartOfDay())
                .build();

        // Saving the created entity to the database
        Advertisement savedAdvertisement = advertisementRepository.save(advertisement);

        // Converting the saved entity to a DTO
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
    public AdvertisementDTOView findById(String advertisementId) {

        // Retrieving a Task entity by its ID
        Advertisement advertisement = advertisementRepository.findById(advertisementId)
                .orElseThrow(() -> new DataNotFoundException("Advertisement not found"));

        // Converting the entity to a DTO
        return AdvertisementDTOView.builder()
                .id(Long.valueOf(advertisement.getId()))
                .title(advertisement.getTitle())
                .content(advertisement.getContent())
                .attachments(advertisement.getAttachments())
                .createDateTime(LocalDate.from(advertisement.getCreateDateTime()))
                .expiryDateTime(LocalDate.from(advertisement.getExpiryDateTime()))
                .build();

    }

    @Override
    public void update(AdvertisementDTOForm advertisementDTOForm) {

        // checking if the advertisement exists
      Advertisement existingAdvertisement = advertisementRepository.findById(String.valueOf(advertisementDTOForm.getId()))
              .orElseThrow(() -> new DataNotFoundException("Advertisement not found"));

        // Updating the existing entity using the DTO
      existingAdvertisement.setTitle(advertisementDTOForm.getTitle());
      existingAdvertisement.setContent(advertisementDTOForm.getContent());
      existingAdvertisement.setAttachments(advertisementDTOForm.getAttachments());
      existingAdvertisement.setCreateDateTime(advertisementDTOForm.getCreateDateTime().atStartOfDay());
      existingAdvertisement.setExpiryDateTime(advertisementDTOForm.getExpiryDateTime().atStartOfDay());

        // Saving the updated entity to the database
      advertisementRepository.save(existingAdvertisement);

    }

    @Override
    public void delete(String id) {
        if (id == null) throw new IllegalArgumentException("advertisement ID must not be null");
        if (!advertisementRepository.existsById(id)) throw new DataNotFoundException("advertisement not found with id: " + id);
        advertisementRepository.deleteById(id);

    }

    @Override
    public List<Object> findAdvertisementsByPersonId(Long personId) {
        // Retrieving advertisements associated with a person by their ID
        List<Advertisement> advertisementList = advertisementRepository.findByPerson_Id(personId);
        return advertisementList.stream()
                .map(this::convertToAdvertisementDTOView)
                .collect(Collectors.toList());
    }

    @Override
    public Object convertToAdvertisementDTOView(Advertisement advertisement) {
        return null ;
    }

    @Override
    public AdvertisementDTOView display(AdvertisementDTOForm advertisementDTOForm) {
        AdvertisementDTOView AdvertisementDTOView = se.lexicon.marketplaceapi.domain.dto.AdvertisementDTOView.builder().build();
        return AdvertisementDTOView;
    }

    @Override
    public LocalDateTime getCreateDateTime(AdvertisementDTOForm advertisementDTOForm) {
        return LocalDateTime.now();
    }


}

package se.lexicon.marketplaceapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.marketplaceapi.Repository.AdvertisementRepository;
import se.lexicon.marketplaceapi.Repository.PersonRepository;
import se.lexicon.marketplaceapi.domain.entity.Advertisement;
import se.lexicon.marketplaceapi.domain.entity.Person;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class AdvertisementRepositoryTest {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private PersonRepository personRepository;

    private Person person;

    void setup() {
        LocalDate today = LocalDate.now();
        person = personRepository.save(new Person("John"));

        advertisementRepository.save(new Advertisement("b0001", "volvo", "jkhbnogdhn", "hjfgh", "2024-10-29", "2024-11-29"));
    }

    @Test
    public void testFindByTitleContaining() {
        List<Advertisement> byTitleContains = advertisementRepository.findByTitleContains("volvo");

        assertEquals(1, byTitleContains.size());
    }

    @Test
    void testFindByPerson_Id() {
        Long id = person.getId();

        List<Advertisement> byPersonId = advertisementRepository.findByPerson_Id(id);

        assertEquals(1, byPersonId.size());
    }
}

package se.lexicon.marketplaceapi.Service;

import se.lexicon.marketplaceapi.domain.dto.PersonDTOForm;
import se.lexicon.marketplaceapi.domain.dto.PersonDTOView;

import java.util.List;

public interface PersonService {

    PersonDTOView create(PersonDTOForm personDTOForm);

    PersonDTOView findById(Long id);

    List<PersonDTOView> findAll();

    PersonDTOView update(PersonDTOForm personDTOForm);

    void delete(Long id);
}

package se.lexicon.marketplaceapi.Converter;

import org.springframework.stereotype.Component;
import se.lexicon.marketplaceapi.domain.dto.PersonDTOForm;
import se.lexicon.marketplaceapi.domain.dto.PersonDTOView;
import se.lexicon.marketplaceapi.domain.entity.Person;

@Component
public interface PersonConverter {

    PersonDTOView toPersonDTO(PersonDTOView entity);

    Person toEntity(PersonDTOForm dto);
}

package se.lexicon.marketplaceapi.Converter.Impl;

import org.springframework.stereotype.Component;
import se.lexicon.marketplaceapi.Converter.PersonConverter;
import se.lexicon.marketplaceapi.domain.dto.PersonDTOForm;
import se.lexicon.marketplaceapi.domain.dto.PersonDTOView;
import se.lexicon.marketplaceapi.domain.entity.Person;

@Component
public class PersonConverterImpl implements PersonConverter {

    @Override
    public PersonDTOView toPersonDTO(PersonDTOView entity) {
        return PersonDTOView.builder().id(entity.getId()).name(entity.getName()).build();
    }

    @Override
    public Person toEntity(PersonDTOForm dto) {
        return Person.builder().id(dto.getId()).name(dto.getName()).build();
    }
}

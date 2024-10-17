package se.lexicon.marketplaceapi.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.marketplaceapi.Exception.DataNotFoundException;
import se.lexicon.marketplaceapi.Repository.PersonRepository;
import se.lexicon.marketplaceapi.Service.PersonService;
import se.lexicon.marketplaceapi.domain.dto.PersonDTOForm;
import se.lexicon.marketplaceapi.domain.dto.PersonDTOView;
import se.lexicon.marketplaceapi.domain.entity.Person;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public PersonDTOView create(PersonDTOForm personDTOForm) {
        if (personDTOForm == null) throw new IllegalArgumentException("PersonDto was null.");

        Person person = new Person(personDTOForm.getName());
        Person savedPerson = personRepository.save(person);
        return PersonDTOView.builder().id(savedPerson.getId()).name(savedPerson.getName()).build();
    }

    @Override
    public PersonDTOView findById(Long id) {
        Person person = (Person) personRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Person id is not valid."));
        return PersonDTOView.builder().id(person.getId()).name(person.getName()).build();
    }

    @Override
    public List<PersonDTOView> findAll() {
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(person -> PersonDTOView.builder().id(person.getId()).name(person.getName()).build())
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTOView update(PersonDTOForm personDTOForm) {
        Person person = (Person) personRepository.findById(personDTOForm.getId()).orElseThrow(() -> new DataNotFoundException("Person Id is not valid."));
        person.setName(personDTOForm.getName());
        return PersonDTOView.builder().id(person.getId()).name(person.getName()).build();
    }

    @Override
    public void delete(Long id) {
        Person person = (Person) personRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Person id is not valid."));
        personRepository.delete(person);
    }
}


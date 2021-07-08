package one.digitalinnovation.personapi.services;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import one.digitalinnovation.personapi.dto.PersonDto;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.exceptions.PersonNotFoundException;
import one.digitalinnovation.personapi.mappers.PersonMapper;
import one.digitalinnovation.personapi.repositories.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	public PersonDto findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}
	
	public ResponseEntity<Person> insert(PersonDto personDto) {
		
		Person personToSave = personMapper.toModel(personDto);
		
		Person savedPerson = personRepository.save(personToSave);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(personDto.getId()).toUri();
		return ResponseEntity.created(uri).body(savedPerson);
	}

	public List<PersonDto> listAll() {
		List<Person> people = personRepository.findAll();
		return people.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}

	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);
	}
	
	public Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
		.orElseThrow(() -> new PersonNotFoundException("Person of ID: " + id + " was not found."));
	}
	
}

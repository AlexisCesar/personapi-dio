package one.digitalinnovation.personapi.services;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import one.digitalinnovation.personapi.dto.PersonDto;
import one.digitalinnovation.personapi.entities.Person;
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
	
	public ResponseEntity<Person> findById(Long id) {
		Optional<Person> person = personRepository.findById(id);
		return ResponseEntity.ok().body(person.orElse(null));
	}
	
	public ResponseEntity<Person> insert(PersonDto personDto) {
		
		Person personToSave = personMapper.toModel(personDto);
		
		Person savedPerson = personRepository.save(personToSave);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(personDto.getId()).toUri();
		return ResponseEntity.created(uri).body(savedPerson);
	}
}

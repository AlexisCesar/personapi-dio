package one.digitalinnovation.personapi.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.personapi.dto.PersonDto;
import one.digitalinnovation.personapi.entities.Person;
import one.digitalinnovation.personapi.services.PersonService;

@RestController
@RequestMapping(value="/people")
public class PersonResource {
	
	private PersonService personService;
	
	@Autowired
	public PersonResource(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Person> findById(@PathVariable Long id) {
		return personService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Person> insert(@RequestBody @Valid PersonDto personDto) {
		return personService.insert(personDto);
	}
	
	@GetMapping
	public List<PersonDto> findAll() {
		return personService.listAll();
	}
	
}

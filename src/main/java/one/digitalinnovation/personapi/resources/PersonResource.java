package one.digitalinnovation.personapi.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/people")
public class PersonResource {
	
	@GetMapping
	public String getPerson() {
		return "<h1>API Test</h1>";
	}
	
}

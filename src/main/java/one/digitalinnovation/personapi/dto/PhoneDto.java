package one.digitalinnovation.personapi.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.entities.enums.PhoneType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private PhoneType type;
	
	@NotEmpty
	@Size(min = 13, max = 14)
	private String number;

}

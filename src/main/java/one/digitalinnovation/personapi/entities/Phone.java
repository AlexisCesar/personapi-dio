package one.digitalinnovation.personapi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.entities.enums.PhoneType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PhoneType type;
	
	@Column(nullable = false)
	private String number;

}

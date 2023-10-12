package com.management.gym.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_contact") 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contacts implements Serializable{

	
	private static final long serialVersionUID = 2353109548034904254L;

	@EqualsAndHashCode.Include	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_contact")
	private UUID id;
	
	@Size(min = 3, max = 100)
	@Schema(description = "Link or identifier of social network", example = "@Example or https://www.linkedin.com/in/gabriel-santosba/")
	private String socialNetwork;
	
	@Size(max = 12)	
	private String numberPhone;	
	
	@Email
	@Schema(description = "Should email valid.", example = "email@email.com")
	private String email;
	
	
}

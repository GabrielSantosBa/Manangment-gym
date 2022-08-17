package com.management.gym.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_contact") 
@AllArgsConstructor 
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contacts implements Serializable{

	
	private static final long serialVersionUID = 2353109548034904254L;

	@EqualsAndHashCode.Include	
	@Id 
	@GeneratedValue
	@Column(name = "id_contact")
	private UUID id;
	
	@Size(min = 3, max = 100)
	private String instagram;
	
	@Size(max = 12)	
	private String numberPhone;	
	
	@Email
	private String email;
	
	
}

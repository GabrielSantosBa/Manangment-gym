package com.management.gym.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "tb_teacher") 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Teacher implements Serializable{

	
	private static final long serialVersionUID = 4489958219071898520L;
	
	@EqualsAndHashCode.Include	
	@Id 
	@GeneratedValue
	@Column(name = "id_teacher")
	private UUID id;
	
	@NotBlank
	private String name;
	
	@OneToMany
	@JoinColumn(name = "FK_TEACHER")
	private List<Contacts> contacts;	
}

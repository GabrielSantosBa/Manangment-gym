package com.management.gym.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "id_contact")
	private Integer id;
	
	private String instagram;	
	private String numberPhone;	
	private String email;
	
	
	
}

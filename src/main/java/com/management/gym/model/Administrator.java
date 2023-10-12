package com.management.gym.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "tb_administrator") 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Administrator implements Serializable{

	
	private static final long serialVersionUID = 4489958219071898520L;
	
	@EqualsAndHashCode.Include	
	@Id 
	@GeneratedValue
	@Column(name = "id_administrator")
	private Long id;	
	
	private String name;
	
	private String sexo;
	
	private String creci;
}

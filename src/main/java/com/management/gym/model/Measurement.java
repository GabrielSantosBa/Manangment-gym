package com.management.gym.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_measurements")
public class Measurement implements Serializable {

	
	
	private static final long serialVersionUID = 5263382898051365368L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	@EqualsAndHashCode.Include
	@Column(name = "id_measurement")
	private Long id;
	
	@Size(max = 4)
	private double height;
	@Size(max = 4)
	private double weight;
	@Size(max = 4)
	private double biceps;
	@Size(max = 4)
	private double triceps;
	@Size(max = 5)
	private double breastplate;
	@Size(max = 4)
	private double shoulder;
	@Size(max = 4)
	private double waist;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate period;
	
}

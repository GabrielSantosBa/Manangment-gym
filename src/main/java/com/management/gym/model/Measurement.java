package com.management.gym.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "tb_measurements")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Measurement implements Serializable {

	private static final long serialVersionUID = 5263382898051365368L;


	@Id 
	@GeneratedValue
	@EqualsAndHashCode.Include
	@Column(name = "id_measurement")
	private Long id;
	
	@Range(min = 0, max = 4)
	private double height;
	@Range(min = 0, max = 200)
	private double weight;
	@Range(min = 0, max = 300)
	private double biceps;
	@Range(min = 0, max = 200)
	private double triceps;
	@Range(min = 0, max = 200)
	private double breastplate;
	@Range(min = 0, max = 250)
	private double shoulder;
	@Range(min = 0, max = 100)
	private double waist;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateMeasurement;
	
}

package com.management.gym.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private Integer id;
	
	private double height;
	private double weight;
	private double biceps;	
	private double triceps;
	private double breastplate;
	private double shoulder;
	private double waist;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate period;
	
}

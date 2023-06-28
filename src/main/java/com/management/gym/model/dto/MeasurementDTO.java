package com.management.gym.model.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public class MeasurementDTO {

	private UUID id;
	private double height;
	private double weight;
	private double biceps;	
	private double triceps;
	private double breastplate;
	private double shoulder;
	private double waist;
	private LocalDate dateMeasurement;
	
}

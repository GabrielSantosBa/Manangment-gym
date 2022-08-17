package com.management.gym.model.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class MeasurementDTO {

	private UUID id;
	private double height;
	private double weight;
	private double biceps;	
	private double triceps;
	private double breastplate;
	private double shoulder;
	private double waist;
	
}

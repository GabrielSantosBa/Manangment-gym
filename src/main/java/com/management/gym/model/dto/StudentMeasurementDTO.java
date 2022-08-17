package com.management.gym.model.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class StudentMeasurementDTO {

	private UUID id;
	private String name;
	
	private List<MeasurementDTO> Measurements;
	
	
}

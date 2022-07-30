package com.management.gym.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class StudentMeasurementDTO {

	private Long id;
	private String name;
	
	private List<MeasurementDTO> Measurements;
	
	
}

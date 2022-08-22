package com.management.gym.model.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentMeasurementDTO {

	private UUID id;
	private String name;
	
	private List<MeasurementDTO> Measurements;
	
	
}

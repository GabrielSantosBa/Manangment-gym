package com.management.gym.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.model.Measurement;
import com.management.gym.service.MeasurementService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/measurement")
@RestController
public class MeasurementController {

	//TODO Eliminar esse controller
	
	private final MeasurementService measurementService;
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> findStudentMeasurementByPeriod(@PathVariable("id") UUID id, @RequestBody @Valid Measurement measurement ){
		//COrrigir nome do metodo(updateMeasumenteStudent)
		measurementService.updateMeasumenteStudent(id, measurement);
		return ResponseEntity.noContent().build();
	}
	
	
}

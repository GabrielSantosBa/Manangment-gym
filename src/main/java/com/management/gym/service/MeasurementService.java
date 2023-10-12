package com.management.gym.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.model.Measurement;
import com.management.gym.model.Student;
import com.management.gym.repository.MeasurementRepository;
import com.management.gym.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeasurementService {

	private final StudentRepository studentRepository;
	private final MeasurementRepository measurementRepository;

	// Atualizar Measumentes de um student já cadastrado
	public Student updateMeasumenteStudent(Long id, Measurement measurement) {

		if (!studentRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student não encontrado!");
		}
		var student = studentRepository.findById(id).get();
		
		student.getMeasurements().add(measurementRepository.save(measurement));
		
		return studentRepository.save(student);
	}

}

package com.management.gym.service;

import org.springframework.stereotype.Service;

import com.management.gym.model.Measurements;
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
	public Student updateMeasumenteStudent(Long id, Measurements measurement) {

		var student = studentRepository.findById(id).get();

		if (student != null) {
			student.getMeasurements().add(measurement);
		}

		student.setMeasurements(measurementRepository.saveAll(student.getMeasurements()));

		return studentRepository.save(student);
	}

}

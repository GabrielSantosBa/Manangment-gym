package com.management.gym.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.builders.StudentBuilder;
import com.management.gym.model.Student;
import com.management.gym.repository.MeasurementRepository;
import com.management.gym.repository.StudentRepository;


@ExtendWith(MockitoExtension.class)
class MeasurementServiceTest {

	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private MeasurementRepository measurementRepository;

	@InjectMocks
	private MeasurementService measurementService;
	

	private Student student;	
	StudentBuilder studentBuilder = new StudentBuilder();	
	
	
	@BeforeEach
	public void setup() {
		startStudentMock();
	}
	
	
	
	@Test
	void testUpdateMeasumenteStudentWhenIdExists() {
		var measurement = student.getMeasurements().get(0);
		
		when(studentRepository.existsById(student.getId())).thenReturn(true);
		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		when(measurementRepository.save(measurement)).thenReturn(measurement);
		when(studentRepository.save(student)).thenReturn(student);
		
		var studentUpdated = measurementService.updateMeasumenteStudent(student.getId(), measurement);
		
		assertNotNull(studentUpdated);
		assertTrue(studentUpdated.getMeasurements().size() > 1);
	}
	
	
	@Test
	void testUpdateMeasumenteStudentWhenIdNotExists() {
		var measurement = student.getMeasurements().get(0);
		when(studentRepository.existsById(Mockito.any())).thenReturn(false);
		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, ()->  measurementService.updateMeasumenteStudent(student.getId(), measurement));
		
		assertTrue(responseStatusException.getMessage().contains("Student não encontrado!"));
		assertTrue(responseStatusException.getStatus().equals(HttpStatus.NOT_FOUND));
	}
	
	
	@SuppressWarnings("static-access")
	public void startStudentMock() {
			student = studentBuilder.createStudent();
	}

}

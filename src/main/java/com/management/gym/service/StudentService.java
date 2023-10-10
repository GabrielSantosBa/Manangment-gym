package com.management.gym.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.common.Utilities;
import com.management.gym.model.Measurement;
import com.management.gym.model.Student;
import com.management.gym.model.dto.MeasurementDTO;
import com.management.gym.model.dto.StudentDTO;
import com.management.gym.model.dto.StudentMeasurementDTO;
import com.management.gym.repository.ContactRepository;
import com.management.gym.repository.MeasurementRepository;
import com.management.gym.repository.StudentRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StudentService  {
	
	private static final String STUDENT_NOT_FOUND = "Student not found!";
	private final StudentRepository studentRepository;
	private final MeasurementRepository measurementRepository;	
	private final ContactRepository contactStudentRepository;	
	private final ModelMapper modelMapper;
	private final Utilities methodUtil;
	
	
	public List<Student> listStudentsBy( boolean status){
		return studentRepository.findStudentByStatus( status);
	}
	
	public List<Student> listAllStudents() {
		if(CollectionUtils.isEmpty(studentRepository.findAll())) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Data Show!");
		}
		return studentRepository.findAll();
	}
	
	public StudentDTO listById( UUID id ) {
		Optional<Student> student = studentRepository.findById( id );
		if(!student.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, STUDENT_NOT_FOUND);
		return modelMapper.map(student.get(), StudentDTO.class);   
	}

	public StudentMeasurementDTO listMeasurementByPeriod(LocalDate iniDate, LocalDate finalDate, UUID id) {
		var studentMeasurment = studentRepository.findMeasurementByDateMeasurement(iniDate, finalDate, id);
		if(!studentMeasurment.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, STUDENT_NOT_FOUND);
		
		return modelMapper.map(studentMeasurment.get(), StudentMeasurementDTO.class);
	}

	public Student createStudent(Student student) {
		student.setContacts(contactStudentRepository.saveAll(student.getContacts()));
		student.setMeasurements(measurementRepository.saveAll(student.getMeasurements()));
		return studentRepository.save(student);
	}

	public void updateStudent(StudentDTO student) {
		
		Optional<Student> studentFound = studentRepository.findById(student.getId());
		if(!studentFound.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, STUDENT_NOT_FOUND);
		try {
			BeanUtils.copyProperties(student, studentFound.get());
			BeanUtils.copyProperties(student.getContacts(), studentFound.get().getContacts());
			studentRepository.save(studentFound.get());
			contactStudentRepository.saveAll(student.getContacts());
		} catch (RuntimeException err) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, STUDENT_NOT_FOUND, err);
		}
		
	}

	public void addUpdateMeasurement(@Valid MeasurementDTO measurementDTO, UUID id) {
		Optional<Student> student = studentRepository.findById(id);
		if(!student.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, STUDENT_NOT_FOUND);
		
		Measurement measurementEntity = (Measurement) methodUtil.convertTo(measurementDTO, Measurement.class);
		
		student.get().getMeasurements().add(measurementEntity);
		measurementRepository.saveAll(student.get().getMeasurements());
		
	}
}

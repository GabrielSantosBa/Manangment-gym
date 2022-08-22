package com.management.gym.service;

import java.time.LocalDate;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.model.Student;
import com.management.gym.model.dto.StudentDTO;
import com.management.gym.model.dto.StudentMeasurementDTO;
import com.management.gym.repository.ContactStudentRepository;
import com.management.gym.repository.MeasurementRepository;
import com.management.gym.repository.StudentRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StudentService  {
	
	private final StudentRepository studentRepository;
	private final MeasurementRepository measurementRepository;	
	private final ContactStudentRepository contactStudentRepository;	
	private final ModelMapper modelMapper;
	
	
	//Retorna todos alunos por verificação de status
	public Page<Student> listStudents( boolean status, Pageable pageable ){
		return studentRepository.findStudentStatus( status, pageable );
	}

	//Retorna todos alunos ativos é inativos
	public Page<Student> listAllStudents( Pageable pageable ) {
		return studentRepository.findAll( pageable );
	}
	
	//Retorna um Student buscado pelo ID
	public StudentDTO listById( UUID id ) {
		
		if(!studentRepository.existsById( id )) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!");
		}
		
		return modelMapper.map(studentRepository.findById( id ).get(), StudentDTO.class);   
	}

	//Retorna as medidas de um aluno informando seu ID é o periodo a ser consultado.
	public StudentMeasurementDTO listMeasurementByPeriod( String iniDate, String finalDate, UUID id ) {
		
		LocalDate initialPeriod = LocalDate.parse( iniDate );
		LocalDate finalPeriod = LocalDate.parse( finalDate );
		
		 var studentMeasurment = studentRepository.findMeasurementByPeriod(initialPeriod, finalPeriod, id);
		
		if(studentMeasurment.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found!");
		
		
		return modelMapper.map(studentMeasurment.get(), StudentMeasurementDTO.class);
	}

	//Salva um aluno completo
	public Student createStudent(Student student) {
		
		student.setContacts(contactStudentRepository.saveAll(student.getContacts()));
		student.setMeasurements(measurementRepository.saveAll(student.getMeasurements()));
		
		return studentRepository.save(student);
	}
}

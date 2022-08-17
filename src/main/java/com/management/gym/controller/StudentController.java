package com.management.gym.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.model.Student;
import com.management.gym.model.dto.StudentDTO;
import com.management.gym.model.dto.StudentMeasurementDTO;
import com.management.gym.service.StudentService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

	
	
	private final StudentService studentService;
	
	@GetMapping
	public Page<Student> listsAllStudents(Pageable pageable){
		return studentService.listAllStudents(pageable);
	}
	
	@GetMapping("/status")
	public Page<Student> listsStudentsActive(@RequestParam boolean status, Pageable pageable){
		return studentService.listStudents(status,pageable);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDTO> listStudentsById(@PathVariable("id") UUID id){
		return ResponseEntity.ok().body(studentService.listById(id));
	}
	
	
	@GetMapping("/measurements/{id}")
	public ResponseEntity<StudentMeasurementDTO> findStudentMeasurementByPeriod(
			@PathVariable("id") UUID id, 
			@RequestParam(value = "minPeriod") String minPeriod,
			@RequestParam(value = "maxPeriod") String maxPeriod){
		
		return ResponseEntity.ok().body(studentService.listMeasurementByPeriod(minPeriod, maxPeriod, id));
	}
	
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody @Valid Student student){
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
	}
	
}

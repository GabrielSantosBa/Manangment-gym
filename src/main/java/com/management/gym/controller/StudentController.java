package com.management.gym.controller;

import java.time.LocalDate;
import java.util.UUID;

import javax.validation.Valid;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.model.Student;
import com.management.gym.model.dto.MeasurementDTO;
import com.management.gym.model.dto.StudentDTO;
import com.management.gym.model.dto.StudentMeasurementDTO;
import com.management.gym.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/student")
@RestController
public class StudentController {
	
	private final StudentService studentService;
	
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "500", description = "Erro Return All Students")
	})
	@GetMapping
	@Operation(summary = "Lists all students in database", tags = {"Student"})
	public Page<Student> listsAllStudents(@ParameterObject Pageable pageable){
		return studentService.listAllStudents(pageable);
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "500", description = "Erro Return Student")
	})
	@GetMapping("/situation")
	@Operation(summary = "Lists a student by situation training(active or inative) in the gym.", tags = {"Student"})
	public Page<Student> listsStudentsByStatus(@RequestParam("status") boolean status, Pageable pageable){
		return studentService.listStudentsBy(status,pageable);
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Student Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@GetMapping("/by-id")
	@Operation(summary = "List a student by (ID).", tags = {"Student"})
	public ResponseEntity<StudentDTO> listStudentsById(@RequestParam("id") UUID id){
		return ResponseEntity.ok().body(studentService.listById(id));
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Student Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@GetMapping("/measurements")
	@Operation(summary = "Returns measurements of a student for a given period", tags = {"Student"})
	public ResponseEntity<StudentMeasurementDTO> findStudentMeasurementByPeriod(
			@RequestParam(value = "id") UUID id, 
			@RequestParam(value = "minPeriod") LocalDate minPeriod,
			@RequestParam(value = "maxPeriod") LocalDate maxPeriod){
		
		return ResponseEntity.ok().body(studentService.listMeasurementByPeriod(minPeriod, maxPeriod, id));
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Operation Success"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@PostMapping
	@Operation(summary = "Creates a student in the database.", tags = {"Student"})
	public ResponseEntity<Student> createStudent(@RequestBody @Valid Student student){
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Student Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@PutMapping
	@Operation(summary = "Updates student data in the database.", tags = {"Student"})
	public ResponseEntity<Void> updateStudent(@RequestBody @Valid StudentDTO student){
		studentService.updateStudent(student);
		return ResponseEntity.noContent().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Student Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@PutMapping("/measurements")
	@Operation(summary = "Updates a student's measurements in the database.", tags = {"Student"})
	public ResponseEntity<Void> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, @RequestParam("id") UUID id){
		studentService.addUpdateMeasurement(measurementDTO,id);
		return ResponseEntity.noContent().build();
	}
	
	
}

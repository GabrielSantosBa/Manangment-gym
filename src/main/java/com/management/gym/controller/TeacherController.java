package com.management.gym.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.model.dto.TeacherDTO;
import com.management.gym.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/teacher")
@RestController
public class TeacherController {
	
	private final TeacherService teacherService;
	
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Erro Return All Teachers")
	})
	@GetMapping
	@Operation(summary = "List all Teacher.", tags = {"Teacher"})
	public List<TeacherDTO> listsAllTeachers(){
		return teacherService.findAllTeachers();
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Teacher Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@GetMapping("/by-id")
	@Operation(summary = "List a teacher by (ID).", tags = {"Teacher"})
	public ResponseEntity<TeacherDTO> listTeacherById(@RequestParam("id") UUID id){
		return ResponseEntity.ok().body(teacherService.listById(id));
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Operation Success"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@PostMapping
	@Operation(summary = "Create a teacher in database).", tags = {"Teacher"})
	public ResponseEntity<TeacherDTO> createTeacher(@RequestBody @Valid TeacherDTO teacherDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacherDto));
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Teacher Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@PutMapping
	@Operation(summary = "Update data a teacher in database).", tags = {"Teacher"})
	public ResponseEntity<Void> updateTeacher(@RequestBody TeacherDTO teacherDto){
		teacherService.updateTeacher(teacherDto);
		return ResponseEntity.noContent().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Teacher Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@DeleteMapping
	@Operation(summary = "Delete a teacher in database).", tags = {"Teacher"})
	public ResponseEntity<String> deleteTeacher(@RequestParam("id") UUID id){
		teacherService.deleteTeacher(id);
		return ResponseEntity.status(HttpStatus.OK).body("Registry Deleted Successfully!");
	}
	
	
}

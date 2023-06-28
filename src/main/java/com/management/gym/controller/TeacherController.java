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

import com.management.gym.model.Teacher;
import com.management.gym.service.TeacherService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/teacher")
@RestController
public class TeacherController {
	
	private final TeacherService teacherService;
	
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Return All Teachers With Success"),
			@ApiResponse(responseCode = "404", description = "Erro Return All Teachers")
	})
	@GetMapping
	public List<Teacher> listsAllTeachers(){
		return teacherService.findAllTeachers();
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Return Teacher By Id With Success"),
			@ApiResponse(responseCode = "400", description = "Teacher Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@GetMapping("/identity")
	public ResponseEntity<Teacher> listTeacherById(@RequestParam("id") UUID id){
		return ResponseEntity.ok().body(teacherService.listById(id));
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Create an new Teacher With Sucess"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@PostMapping
	public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid Teacher teacher){
		return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacher));
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update an Teacher of With Success"),
			@ApiResponse(responseCode = "400", description = "Teacher Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@PutMapping
	public ResponseEntity<Void> updateTeacher(@RequestBody Teacher teacher, @RequestParam("id") UUID id){
		teacherService.updateTeacher(teacher, id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Registry Deleted Successfully"),
			@ApiResponse(responseCode = "400", description = "Teacher Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@DeleteMapping
	public ResponseEntity<String> deleteTeacher(@RequestParam("id") UUID id){
		teacherService.deleteTeacher(id);
		return ResponseEntity.status(HttpStatus.OK).body("Registry Deleted Successfully!");
	}
	
	
}

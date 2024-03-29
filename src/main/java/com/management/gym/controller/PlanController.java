package com.management.gym.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.model.dto.PlanDTO;
import com.management.gym.service.PlanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/plan")
@RestController
@CrossOrigin(origins = "*")
public class PlanController {
	
	private final PlanService planService;
	
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "When Operation Error")
	})
	@GetMapping
	@Operation(summary = "List all Plans.", tags = {"Plan"})
	public List<PlanDTO> listsAllPlans(){
		return planService.findAllPlans();
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Plan Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@GetMapping("/by-id")
	@Operation(summary = "List a Plan by (ID).", tags = {"Plan"})
	public ResponseEntity<PlanDTO> listPlanById(@RequestParam("id") Long id){
		return ResponseEntity.ok().body(planService.listById(id));
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Operation Success"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@PostMapping
	@Operation(summary = "Create a Plan in database).", tags = {"Plan"})
	public ResponseEntity<PlanDTO> createPlan(@RequestBody @Valid PlanDTO planDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(planService.createplan(planDto));
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Plan Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@PutMapping
	@Operation(summary = "Update data a Plan in database).", tags = {"Plan"})
	public ResponseEntity<Void> updatePlan(@RequestBody PlanDTO planDto){
		planService.updateplan(planDto);
		return ResponseEntity.noContent().build();
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Plan Not Found!"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@DeleteMapping
	@Operation(summary = "Delete a Plan in database).", description = "Registry Deleted Successfully", tags = {"Plan"})
	public ResponseEntity<String> deletePlan(@RequestParam("id") Long id){
		planService.deleteplan(id);
		return ResponseEntity.status(HttpStatus.OK).body("Registry Deleted Successfully!");
	}
	
	
}

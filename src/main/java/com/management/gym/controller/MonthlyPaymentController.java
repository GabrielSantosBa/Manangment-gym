package com.management.gym.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.model.dto.MonthlyPaymentDTO;
import com.management.gym.service.MonthlyPaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/monthly-payment")
@RestController
public class MonthlyPaymentController {

	
	private final MonthlyPaymentService monthlyPaymentService;
	
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Return Fail Operation"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@Operation(summary = "Finds all Monthly Payment os a student by financia status (in day, late, in negociation).", tags = {"Monthly-payment"})
	@GetMapping
	public Page<MonthlyPaymentDTO> findMonthlyPaymentByStudentAndFinancialStatus(
			@RequestParam("financialStatus") Integer financialCod, Pageable pageable,
			@RequestParam("idStudent") Long idStudent){
		return monthlyPaymentService.findStudentsWithStatusPayment(financialCod, idStudent, pageable);
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Return Fail Operation"),
			@ApiResponse(responseCode = "500", description = "Error Intern")
	})
	@Operation(summary = "Creates a Monthly Payment.", tags = {"Monthly-payment"})
	@PostMapping
	public MonthlyPaymentDTO createMonthlyPayment(@RequestBody @Valid MonthlyPaymentDTO monthlyPaymentDTO){
		return monthlyPaymentService.createMonthlyPaymentStudent(monthlyPaymentDTO);
	}
	
}

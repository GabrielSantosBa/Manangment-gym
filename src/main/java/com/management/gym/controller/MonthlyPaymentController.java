package com.management.gym.controller;

import java.util.UUID;

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

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/monthly-payment")
@RestController
public class MonthlyPaymentController {

	
	private final MonthlyPaymentService monthlyPaymentService;
	
	@GetMapping
	public Page<MonthlyPaymentDTO> findMonthlyPaymentByStudentAndFinancialStatus(
			@RequestParam("financialStatus") Integer financialCod, Pageable pageable,
			@RequestParam("idStudent") UUID idStudent){
		return monthlyPaymentService.findStudentsWithStatusPayment(financialCod, idStudent, pageable);
	}
//	
//
//	@GetMapping("/delayed-payments")
//	public Page<MonthlyPaymentDTO> listsAllMonthlyPaymentLate(Pageable pageable){
//		return monthlyPaymentService.findAllStudentsMonthlyPaymentLate(pageable);
//	}
	
	@PostMapping
	public MonthlyPaymentDTO createMonthlyPayment(@RequestBody @Valid MonthlyPaymentDTO monthlyPaymentDTO){
		return monthlyPaymentService.createMonthlyPaymentStudent(monthlyPaymentDTO);
	}
	
}

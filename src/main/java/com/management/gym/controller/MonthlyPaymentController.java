package com.management.gym.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.model.dto.MonthlyPaymentDTO;
import com.management.gym.service.MonthlyPaymentService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/monthly-payment")
@RestController
public class MonthlyPaymentController {

	
	private final MonthlyPaymentService monthlyPaymentService;
	
	@GetMapping("/delayed-payments/{id}/{status}")
	public Page<MonthlyPaymentDTO> listsAllStudentsWithStatusPayment(
			@PathVariable("id") UUID id, 
			@PathVariable("status") Integer status, Pageable pageable){
		return monthlyPaymentService.findStudentsWithStatusPayment(id, status, pageable);
	}
	

	@GetMapping("/delayed-payments")
	public Page<MonthlyPaymentDTO> listsAllMonthlyPaymentLate(Pageable pageable){
		return monthlyPaymentService.findAllStudentsMonthlyPaymentLate(pageable);
	}
	
}

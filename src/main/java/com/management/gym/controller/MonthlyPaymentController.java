package com.management.gym.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.enums.FinancialStatusEnum;
import com.management.gym.model.MonthlyPayment;
import com.management.gym.service.MonthlyPaymentService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/monthly-payment")
@RestController
public class MonthlyPaymentController {

	
	
	private final MonthlyPaymentService monthlyPaymentService;
	
	@GetMapping("/delayed-payments/{id}")
	public Page<MonthlyPayment> listsAllStudentsWithStatusPayment(
			@PathVariable("id") Long id, 
			@RequestParam FinancialStatusEnum status, 
			Pageable pageable){
		return monthlyPaymentService.findAllStudentsWithLatePayment(id, status, pageable);
	}
	

	@GetMapping("/delayed-payments")
	public Page<MonthlyPayment> listsAllMonthlyPaymentLate(Pageable pageable){
		return monthlyPaymentService.findAllStudentsMonthlyPaymentLate(pageable);
	}
	
//	@GetMapping("/status")
//	public Page<Student> listsStudentsActive(@RequestParam boolean status, Pageable pageable){
//		return studentService.listStudents(status,pageable);
//	}
//	
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<StudentDTO> listStudentsById(@PathVariable("id") Long id){
//		return ResponseEntity.ok().body(studentService.listById(id));
//	}
//	
//	
//	@GetMapping("/measurements/{id}")
//	public ResponseEntity<StudentMeasurementDTO> findStudentMeasurementByPeriod(
//			@PathVariable("id") Long id, 
//			@RequestParam(value = "minPeriod") String minPeriod,
//			@RequestParam(value = "maxPeriod") String maxPeriod){
//		
//		return ResponseEntity.ok().body(studentService.listMeasurementByPeriod(minPeriod, maxPeriod, id));
//	}
//	
//	
//	@PostMapping
//	public ResponseEntity<Student> createStudent(@RequestBody @Valid Student student){
//		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
//	}
	
}

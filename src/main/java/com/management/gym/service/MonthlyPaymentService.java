package com.management.gym.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.gym.model.MonthlyPayment;
import com.management.gym.model.dto.MonthlyPaymentDTO;
import com.management.gym.repository.MonthlyPaymentRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MonthlyPaymentService  {
	
	private final MonthlyPaymentRepository monthlyPaymentRepository;
	

	
	public Page<MonthlyPaymentDTO> findStudentsWithStatusPayment(UUID id, Integer status, Pageable pageable) {
		//return monthlyPaymentRepository.findStudentByFinancialStatus(id, status, pageable);
		
		Page<MonthlyPayment> retorno = monthlyPaymentRepository.findByFinancialStatusEnumAndStudent(status, id, pageable);
		
		System.out.println("OHOHOH : "+retorno.toString());
		
		return null;
	}

	public Page<MonthlyPaymentDTO> findAllStudentsMonthlyPaymentLate(Pageable pageable) {
		return monthlyPaymentRepository.findAllAccountsLatePayment(pageable);
	}
	
}

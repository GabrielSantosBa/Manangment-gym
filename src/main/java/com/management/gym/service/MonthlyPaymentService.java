package com.management.gym.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.management.gym.enums.FinancialStatusEnum;
import com.management.gym.model.MonthlyPayment;
import com.management.gym.repository.MonthlyPaymentRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MonthlyPaymentService  {
	
	private final MonthlyPaymentRepository monthlyPaymentRepository;
	
	
	
	private final ModelMapper modelMapper;
	
	
	//Retorna todos alunos por verificação de status
//	public Page<Student> listStudents( boolean status, Pageable pageable ){
//		return studentRepository.findStudentStatus( status, pageable );
//	}

	//Retorna todos alunos ativos é inativos
	public Page<MonthlyPayment> findAllStudentsWithLatePayment(Long id, FinancialStatusEnum status, Pageable pageable ) {
		return monthlyPaymentRepository.findStudentWithLatePayment(id, status.toString(), pageable);
	}
	
	
}

package com.management.gym.service;

import static java.util.stream.Collectors.toList;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.common.Utilities;
import com.management.gym.model.MonthlyPayment;
import com.management.gym.model.Student;
import com.management.gym.model.dto.MonthlyPaymentDTO;
import com.management.gym.repository.MonthlyPaymentRepository;
import com.management.gym.repository.StudentRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MonthlyPaymentService  {
	
	private final MonthlyPaymentRepository monthlyPaymentRepository;
	private final StudentRepository studentRepository;
	private final Utilities methodsUtil;

	
	public Page<MonthlyPaymentDTO> findStudentsWithStatusPayment(Integer financialCod, UUID idStudent, Pageable pageable) {
		Page<MonthlyPayment> monthlyPaymentEntity = monthlyPaymentRepository.findByFinancialStatusCodAndStudent(financialCod, idStudent, pageable);
		
		if(ObjectUtils.isEmpty(monthlyPaymentEntity)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
		
		return new PageImpl<MonthlyPaymentDTO>(monthlyPaymentEntity.getContent().stream().map(item -> (MonthlyPaymentDTO) methodsUtil.convertTo(item, MonthlyPaymentDTO.class))
				                                  .collect(toList()));
	}

//	public Page<MonthlyPaymentDTO> findAllStudentsMonthlyPaymentLate(Pageable pageable) {
//		return monthlyPaymentRepository.findAllAccountsLatePayment(pageable);
//	}

	public MonthlyPaymentDTO createMonthlyPaymentStudent(MonthlyPaymentDTO monthlyPaymentDTO) {
		
		Optional<Student> student = studentRepository.findById(UUID.fromString(monthlyPaymentDTO.getStudentId()));
		if(!student.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
		
		var monthlyPaymentEntity = (MonthlyPayment) methodsUtil.convertTo(monthlyPaymentDTO, MonthlyPayment.class);
		monthlyPaymentEntity.setStudent(student.get());
		
		return (MonthlyPaymentDTO) methodsUtil.convertTo(monthlyPaymentRepository.save(monthlyPaymentEntity), MonthlyPaymentDTO.class);
	}
	
}

package com.management.gym.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.management.gym.model.MonthlyPayment;
import com.management.gym.model.Student;
import com.management.gym.model.dto.MonthlyPaymentDTO;

public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment, UUID>{

	
	//TODO fazer testes de performance com o FETCH JOIN
	@Query("SELECT new com.management.gym.model.dto.MonthlyPaymentDTO(payment.financialStatusEnum, payment.formOfPayment, payment.monthlyfee, student.birthDate, student.name )"
			+ " FROM tb_monthly_payment payment JOIN payment.student student "
			+ " WHERE student.id = :id_student and payment.financialStatusEnum = :status")
			Page<MonthlyPaymentDTO> findAllByStatusById_student(@Param("id_student") UUID id_student, @Param("status") Integer status, Pageable pageable);

	
	Page<MonthlyPayment> findByFinancialStatusEnumAndStudent(Integer status, UUID id_student, Pageable pageable);
	
	
	@Query("SELECT new com.management.gym.model.dto.MonthlyPaymentDTO(payment.financialStatusEnum, payment.formOfPayment, payment.monthlyfee, student.birthDate, student.name)"
			+ " FROM tb_monthly_payment payment JOIN payment.student student "
			+ " WHERE payment.financialStatusEnum = 2")
	Page<MonthlyPaymentDTO> findAllAccountsLatePayment(Pageable pageable);
	
}

package com.management.gym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.gym.enums.FinancialStatusEnum;
import com.management.gym.model.MonthlyPayment;
import com.management.gym.model.Student;

public interface MonthlyPaymentRepository extends JpaRepository<Student, Long>{

	
	
	@Query(value = "SELECT * FROM tb_monthly_payment payment "
			+ "INNER JOIN tb_students student "
			+ "ON payment.id_student = student.id "
			+ "WHERE student.id = ?1 AND payment.status = ?2", nativeQuery = true)
	public Page<MonthlyPayment> findStudentWithLatePayment(Long id_student, String status, Pageable pageable);
	
	
}

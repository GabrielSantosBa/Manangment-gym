package com.management.gym.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.gym.model.MonthlyPayment;
import com.management.gym.model.Student;

public interface MonthlyPaymentRepository extends JpaRepository<Student, Long>{

	
	
	@Query(value = "SELECT * FROM tb_monthly_payment payment "
			+ " INNER JOIN tb_students student "
			+ " ON payment.fk_student = ?1 "
			+ " WHERE payment.status = ?2", nativeQuery = true)
	public Page<MonthlyPayment> findStudentWithLatePayment(Long id_student, String status, Pageable pageable);

	
	
	@Query(value = "SELECT * FROM tb_monthly_payment payment "
			+ " INNER JOIN tb_students student "
			+ " ON payment.fk_student = student.id_student "
			+ " WHERE payment.status = 'LATE'", nativeQuery = true)
	public Page<MonthlyPayment> findAllAccountsLatePayment(Pageable pageable);
	
	
}

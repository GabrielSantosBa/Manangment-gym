package com.management.gym.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.gym.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	
	//TODO trocar ID_aluno por id_student
	
	@Query(value = "SELECT * FROM tb_students student WHERE student.status = ?1", nativeQuery = true)
	public Page<Student> findStudentStatus(boolean status, Pageable pageable);
	
	
	@Query(value = "SELECT * FROM tb_students student INNER JOIN tb_measurements measure ON student.id = measure.id_aluno AND measure.period BETWEEN ?1 AND ?2 WHERE student.id = ?3 ", nativeQuery = true)
	public Student findMeasurementByPeriod(LocalDate iniPeriod, LocalDate lastPeriod, Long id);
	
	
//	@Query(value = "SELECT * FROM tb_students student INNER JOIN tb_monthly_payment payment ON payment.id_student = student.id WHERE payment.status = :financialStatus", nativeQuery = true)
//	public Page<Student> findStudentWithMonthlyPayment(FinancialStatusEnum financialStatus, Pageable pageable);
	
	
}
package com.management.gym.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.gym.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query(value = "SELECT * FROM tb_students student WHERE student.situation_training = ?1", nativeQuery = true)
	public Page<Student> findStudentStatus(boolean status, Pageable pageable);
	
	
	@Query(value = "SELECT * FROM tb_students student "
			+ "	INNER JOIN tb_measurements measure ON student.id_student = measure.fk_student "
			+ "	AND measure.period BETWEEN ?1 AND ?2"
			+ "	WHERE student.id_student = ?3", nativeQuery = true)
	public Optional<Student> findMeasurementByPeriod(LocalDate iniPeriod, LocalDate lastPeriod, Long id);
	
}

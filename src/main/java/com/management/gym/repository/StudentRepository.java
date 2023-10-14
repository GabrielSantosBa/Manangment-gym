package com.management.gym.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.management.gym.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

//	@Query(value = "SELECT * FROM tb_students student WHERE student.situation_training = ?1", nativeQuery = true)
//	public Page<Student> findStudentStatus(boolean status, Pageable pageable);
	
	public List<Student> findStudentBySituation(String situation);
	
	
	@Query(value = "SELECT * FROM tb_students student "
			+ "	INNER JOIN tb_measurements measure ON student.id_student = measure.fk_student "
			+ "	AND measure.date_measurement BETWEEN ?1 AND ?2"
			+ "	WHERE student.id_student = ?3", nativeQuery = true)
	 Optional<Student> findMeasurementByDateMeasurement(LocalDate iniPeriod, LocalDate lastPeriod, Long id);
	
	
	//Optional<Student> findStudentByIdAndMeasurementsBetween (UUID id, LocalDate iniPeriod, LocalDate lastPeriod);
	
}

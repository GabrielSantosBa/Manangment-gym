package com.management.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.model.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long>{

	
}

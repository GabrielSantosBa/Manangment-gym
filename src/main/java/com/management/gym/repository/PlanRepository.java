package com.management.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{

	
	
}

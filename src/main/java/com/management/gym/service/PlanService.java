package com.management.gym.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.model.Plan;
import com.management.gym.model.dto.PlanDTO;
import com.management.gym.repository.PlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class PlanService {

	private final PlanRepository planRepository;

	public List<Plan>findAllPlans() {
		List<Plan> plans = planRepository.findAll();
		
		if(ObjectUtils.isEmpty(plans)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No plans for view");
		
		return plans;
	}

	public Plan listById(UUID id) {
		Optional<Plan> planReturn = planRepository.findById(id);
		
		if(!planReturn.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plan Not found.");
		
		return planReturn.get();
	}
	
	public Plan createplan(@Valid Plan plan) {
		return planRepository.save(plan);
	}

	public void updateplan(@Valid PlanDTO planDto, UUID id) {
		Optional<Plan> planActualy = planRepository.findById(id);
		
		if(!planActualy.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plan Not found.");
		
		BeanUtils.copyProperties(planDto, planActualy.get());
		
		planRepository.save(planActualy.get());
	}

	public void deleteplan(UUID id) {
		boolean existsById = planRepository.existsById(id);
		
		if(!existsById) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "plan Not found.");
		
		planRepository.deleteById(id);
	}
	
	
}

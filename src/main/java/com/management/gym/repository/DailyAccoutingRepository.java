package com.management.gym.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.model.DailyAccounting;

public interface DailyAccoutingRepository extends JpaRepository<DailyAccounting, UUID>{

	List<DailyAccounting> findByAccountingDateBetween(LocalDate iniDay, LocalDate finalDay);

}

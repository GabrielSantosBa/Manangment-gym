package com.management.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.model.Contacts;

public interface ContactRepository extends JpaRepository<Contacts, Long>{

	
}

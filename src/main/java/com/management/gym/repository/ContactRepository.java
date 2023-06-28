package com.management.gym.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.model.Contacts;

public interface ContactRepository extends JpaRepository<Contacts, UUID>{

	
}

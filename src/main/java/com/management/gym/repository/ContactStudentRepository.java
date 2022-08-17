package com.management.gym.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.model.Contacts;

public interface ContactStudentRepository extends JpaRepository<Contacts, UUID>{

	
}

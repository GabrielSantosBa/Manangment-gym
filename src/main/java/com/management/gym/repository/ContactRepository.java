package com.management.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.gym.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

	
}

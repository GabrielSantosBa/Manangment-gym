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

import com.management.gym.model.Contacts;
import com.management.gym.model.Teacher;
import com.management.gym.repository.ContactRepository;
import com.management.gym.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class TeacherService {

	private final TeacherRepository teacherRepository;
	private final ContactRepository contactsRepository;

	public List<Teacher>findAllTeachers() {
		List<Teacher> teachers = teacherRepository.findAll();
		
		if(ObjectUtils.isEmpty(teachers)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Teachers for view");
		
		return teachers;
	}

	public Teacher listById(UUID id) {
		Optional<Teacher> teacherReturn = teacherRepository.findById(id);
		
		if(!teacherReturn.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher Not found.");
		
		return teacherReturn.get();
	}

	
	public Teacher createTeacher(@Valid Teacher teacher) {
		
		teacher.setContacts(contactsRepository.saveAll(teacher.getContacts()));
		
		return teacherRepository.save(teacher);
	}

	public void updateTeacher(@Valid Teacher teacher, UUID id) {
		Optional<Teacher> teacherActualy = teacherRepository.findById(id);
		
		if(!teacherActualy.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher Not found.");
		
		BeanUtils.copyProperties(teacher, teacherActualy.get());
		
		
		
		teacherRepository.save(teacherActualy.get());
		
	}

	public void deleteTeacher(UUID id) {
		
		boolean existsById = teacherRepository.existsById(id);
		
		if(!existsById) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher Not found.");
		
		teacherRepository.deleteById(id);
	}
	
	
}

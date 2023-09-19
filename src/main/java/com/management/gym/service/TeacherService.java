package com.management.gym.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static java.util.stream.Collectors.*;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.common.Utilities;
import com.management.gym.model.Teacher;
import com.management.gym.model.dto.TeacherDTO;
import com.management.gym.repository.ContactRepository;
import com.management.gym.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {

	private final TeacherRepository teacherRepository;
	private final ContactRepository contactsRepository;
	private final Utilities methodUtil;
	

	public List<TeacherDTO>findAllTeachers() {
		List<Teacher> teachers = teacherRepository.findAll();
		
		if(ObjectUtils.isEmpty(teachers)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Teachers for view");
		
		var listTeacherDTO = teachers.stream().map(t -> (TeacherDTO) methodUtil.convertTo(t, TeacherDTO.class)).collect(toList());
		
		return listTeacherDTO;
	}

	public TeacherDTO listById(UUID id) {
		Optional<Teacher> teacherReturn = teacherRepository.findById(id);
		
		if(!teacherReturn.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher Not found.");
		
		return (TeacherDTO) methodUtil.convertTo(teacherReturn.get(), TeacherDTO.class);
	}

	
	public TeacherDTO createTeacher(@Valid TeacherDTO teacherDto) {
		
		teacherDto.setContacts(contactsRepository.saveAll(teacherDto.getContacts()));
		
		var teacherEntity = (Teacher) methodUtil.convertTo(teacherDto, Teacher.class);
		
		teacherRepository.save(teacherEntity);
		
		return (TeacherDTO) methodUtil.convertTo(teacherRepository.save(teacherEntity), TeacherDTO.class);
	}

	public void updateTeacher(@Valid TeacherDTO teacherDto) {
		Optional<Teacher> teacherActualy = teacherRepository.findById(teacherDto.getId());
		
		if(!teacherActualy.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher Not found.");
		
		BeanUtils.copyProperties(teacherDto.getContacts(), teacherActualy.get().getContacts());		
		BeanUtils.copyProperties(teacherDto, teacherActualy.get());
		
		contactsRepository.saveAll(teacherActualy.get().getContacts());		
		teacherRepository.save(teacherActualy.get());
	}

	public void deleteTeacher(UUID id) {
		
		boolean existsById = teacherRepository.existsById(id);
		
		if(!existsById) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher Not found.");
		
		teacherRepository.deleteById(id);
	}
	
	
}

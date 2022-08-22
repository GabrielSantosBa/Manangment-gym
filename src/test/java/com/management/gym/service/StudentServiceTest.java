package com.management.gym.service;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.management.gym.repository.StudentRepository;



@SpringBootTest
class StudentServiceTest {
	
	
	
	@InjectMocks
	private StudentService studentService;
	
	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	

	@Test
	void testListStudents() {
		fail("Not yet implemented");
	}

	@Test
	void testListAllStudents() {
		fail("Not yet implemented");
	}

	@Test
	void testListById() {
		fail("Not yet implemented");
	}

	@Test
	void testListMeasurementByPeriod() {
		fail("Not yet implemented");
	}

	@Test
	void testCreateStudent() {
		fail("Not yet implemented");
	}

	@Test
	void testStudentService() {
		fail("Not yet implemented");
	}

}

package com.management.gym.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.builders.StudentBuilder;
import com.management.gym.model.Student;
import com.management.gym.model.dto.StudentDTO;
import com.management.gym.model.dto.StudentMeasurementDTO;
import com.management.gym.repository.ContactRepository;
import com.management.gym.repository.MeasurementRepository;
import com.management.gym.repository.StudentRepository;


@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	

	private static final String STUDENT_NOT_FOUND = "Student not found!";

	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private ContactRepository contactStudentRepository;
	
	@Mock
	private MeasurementRepository measurementRepository;
	

	@Mock
	private ModelMapper modelMapper;
	
	@InjectMocks
	private StudentService studentService;

	private Student student;	
	private StudentDTO studentDTO;	
	StudentBuilder studentBuilder = new StudentBuilder();	
	private StudentMeasurementDTO studentMeasurementDTO;
	
	
	@BeforeEach
	public void setup() {
		startStudentMock();
		startStudentDTOMock();
		startCreateStudentMeasurementDTO();
	}
	

	@Test
	void testListStudentsByStatus() {
		
		List<Student> listStudentMock = List.of(student);
		Pageable pageable = PageRequest.of(0, listStudentMock.size());
		var studentMockPage = new PageImpl<>(List.of(student), pageable, pageable.getPageSize());
		when(studentRepository.findStudentByStatus(false, pageable)).thenReturn(studentMockPage);
		Page<Student> listStudents = studentService.listStudentsBy(false, pageable);
		
		assertEquals(listStudents.getTotalElements(), listStudentMock.size());
		assertNotNull(listStudents);
		assertTrue(listStudents.getContent().get(0).isStatus() == false);
	}

	@Test
	void testListAllStudents() {
		
		List<Student> listStudentMock = List.of(student);
		Pageable pageable = PageRequest.of(0, listStudentMock.size());
		var studentMockPage = new PageImpl<>(List.of(student), pageable, pageable.getPageSize());
		when(studentRepository.findAll(pageable)).thenReturn(studentMockPage);
		Page<Student> listAllStudents = studentService.listAllStudents(pageable);
		
		assertEquals(listAllStudents.getTotalElements(), listStudentMock.size());
		assertNotNull(listAllStudents);
	}
	
//	@Test
//	void testListMeasurementByPeriodWhenIdExists() {
//		String iniDate = "2022-07-08";
//		String finalDate = "2022-08-08";
//		
//		LocalDate initialPeriod = LocalDate.parse( iniDate );
//		LocalDate finalPeriod = LocalDate.parse( finalDate );
//		
//		when(studentRepository.findMeasurementByPeriod(initialPeriod, finalPeriod, student.getId())).thenReturn(Optional.of(student));
//		when(modelMapper.map(Mockito.any(), Mockito.any())).thenReturn(studentMeasurementDTO);
//		var listById = studentService.listMeasurementByPeriod(iniDate, finalDate, student.getId());
//		assertNotNull(listById);
//		assertEquals(studentMeasurementDTO, listById);
//		assertNotNull(listById.getMeasurements());
//	}
	
//	@Test
//	void testListMeasurementByPeriodWhenIdNotExists() {
//		String iniDate = "2022-07-08";
//		String finalDate = "2022-08-08";
//		
//		LocalDate initialPeriod = LocalDate.parse( iniDate );
//		LocalDate finalPeriod = LocalDate.parse( finalDate );
//		when(studentRepository.findMeasurementByPeriod(initialPeriod, finalPeriod, student.getId())).thenReturn(Optional.empty());
//		ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class, ()-> studentService.listMeasurementByPeriod(iniDate, finalDate, student.getId()));
//		
//		assertTrue(responseStatusException.getMessage().contains(STUDENT_NOT_FOUND));
//		assertTrue(responseStatusException.getStatus().equals(HttpStatus.NOT_FOUND));
//	}

	@Test
	void testCreateStudent() {
		when(studentRepository.save(student)).thenReturn(student);
		when(contactStudentRepository.saveAll(student.getContacts())).thenReturn(student.getContacts());
		when(measurementRepository.saveAll(student.getMeasurements())).thenReturn(student.getMeasurements());
		
		student.setContacts(contactStudentRepository.saveAll(student.getContacts()));
		student.setMeasurements(measurementRepository.saveAll(student.getMeasurements()));
		
		Student studentCreated = studentService.createStudent(student);
		
		assertNotNull(studentCreated);
		assertNotNull(studentCreated.getContacts());
		assertNotNull(studentCreated.getMeasurements());
		assertTrue(student.getContacts().size() == studentCreated.getContacts().size());
		assertTrue(student.getMeasurements().size() == studentCreated.getMeasurements().size());
		
	}
	
	
	
	@SuppressWarnings("static-access")
	public void startStudentMock() {
			student = studentBuilder.createStudent();
	}
	@SuppressWarnings("static-access")
	public void startStudentDTOMock() {
			studentDTO = studentBuilder.createStudentDTO();
	}
	@SuppressWarnings("static-access")
	public void startCreateStudentMeasurementDTO() {
		studentMeasurementDTO = studentBuilder.createStudentMeasurementDTO();
	}
	

}

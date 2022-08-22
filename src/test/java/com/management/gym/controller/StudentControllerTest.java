package com.management.gym.controller;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.gym.builders.StudentBuilder;
import com.management.gym.model.Student;
import com.management.gym.model.dto.StudentDTO;
import com.management.gym.model.dto.StudentMeasurementDTO;
import com.management.gym.service.StudentService;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = {StudentController.class})
@AutoConfigureWebMvc
@AutoConfigureMockMvc
class StudentControllerTest {

	
	
	@MockBean
	private StudentService studentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objMapper;
	
	
	private Student student;	
	private StudentDTO studentDTO;	
	StudentBuilder studentBuilder = new StudentBuilder();	
	private StudentMeasurementDTO studentMeasurementDTO;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		startStudentMock();
		startStudentDTOMock();
		startCreateStudentMeasurementDTO();
	}
	
	@Test
	void testListsAllStudents() throws Exception {
		Pageable pageable = PageRequest.of(0, 20);
		var studentPageMock = new PageImpl<>(List.of(student), pageable, pageable.getPageSize());

		when(studentService.listAllStudents(pageable)).thenReturn(studentPageMock);
		mockMvc.perform(get("/students?page=1&size=20")
				.content(objMapper.writeValueAsBytes(studentPageMock))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		assertNotNull(studentService, "Shoould Inject");
	}
	
	@Test	
	void testListsStudentsByStatus() throws JsonProcessingException, Exception {
		Pageable pageable = PageRequest.of(0, 20);		
		var studentPageMock = new PageImpl<>(List.of(student), pageable, pageable.getPageSize());
		
		when(studentService.listStudents(true,pageable)).thenReturn(studentPageMock);
		
		mockMvc.perform(get("/students/status?status=false")
				.content(objMapper.writeValueAsString(studentPageMock))
				.contentType(MediaType.APPLICATION_JSON))	
		.andExpect(status().isOk());
		assertNotNull(studentService, "Shoould Inject");
	}

	@Test
	void testListStudentsById() throws JsonProcessingException, Exception {
		
		when(studentService.listById(UUID.randomUUID())).thenReturn(studentDTO);	
		mockMvc.perform(get("/students/b269ae57-31e2-44ec-bbb2-3b240a697337")
				.content(objMapper.writeValueAsString(studentDTO))
				.contentType(MediaType.APPLICATION_JSON))	
		.andExpect(status().isOk());
		assertNotNull(studentService, "Shoould Inject");
		
	}
	
	@Test
	void testfindStudentMeasurementByPeriod() throws JsonProcessingException, Exception {
		
		String  DateIni = "2022-07-01";
		String  DateFim = "2022-08-01";
		when(studentService.listMeasurementByPeriod(DateIni, DateFim, student.getId())).thenReturn(studentMeasurementDTO);
		var listMeasurementByPeriod = studentService.listMeasurementByPeriod(DateIni, DateFim, student.getId());
		
		mockMvc.perform(get("/students/measurements/b269ae57-31e2-44ec-bbb2-3b240a697337?minPeriod=2022-07-01&maxPeriod=2022-08-01")
				.content(objMapper.writeValueAsString(listMeasurementByPeriod))
				.contentType(MediaType.APPLICATION_JSON))	
		.andExpect(status().isOk());
		
		assertNotNull(listMeasurementByPeriod);
		assertNotNull(studentService, "Shoould Inject");
		
	}

	@Test
	void testCreateStudent() throws JsonProcessingException, Exception {
		when(studentService.createStudent(student)).thenReturn(new Student());
		
		var createStudent = studentService.createStudent(student);
		mockMvc.perform(post("/students")
				.content(objMapper.writeValueAsString(studentDTO))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
		
		assertNotNull(createStudent);
		assertNotNull(studentService, "Shoould Inject");
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

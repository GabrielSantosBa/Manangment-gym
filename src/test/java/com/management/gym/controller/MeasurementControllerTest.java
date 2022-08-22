package com.management.gym.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.gym.builders.StudentBuilder;
import com.management.gym.model.Measurement;
import com.management.gym.model.Student;
import com.management.gym.service.MeasurementService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = {MeasurementController.class})
@AutoConfigureWebMvc
@AutoConfigureMockMvc
public class MeasurementControllerTest {
	
	
	@MockBean
	private MeasurementService measurementService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objMapper;
	
	
	StudentBuilder studentBuilder = new StudentBuilder();	
	private Student student;	
	private List<Measurement> measurement;
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		startStudentMock();
		startMeasurementMock();
	}
	
	
	@Test
	void whenfindStudentMeasurementByPeriodShouldReturn200() throws JsonProcessingException, Exception {
		when(measurementService.updateMeasumenteStudent(student.getId(), measurement.get(0))).thenReturn(student);
		
		mockMvc.perform(put("/measurement/b269ae57-31e2-44ec-bbb2-3b240a697337")
				.content(objMapper.writeValueAsBytes(student))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
		
	}
	
	
	@SuppressWarnings("static-access")
	public void startStudentMock() {
			student = studentBuilder.createStudent();
	}
	
	
	@SuppressWarnings("static-access")
	public void startMeasurementMock() {
		measurement = studentBuilder.createMeasurement();
	}
	

}

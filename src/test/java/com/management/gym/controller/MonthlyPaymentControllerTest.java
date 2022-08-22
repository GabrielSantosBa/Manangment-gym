package com.management.gym.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.gym.builders.MonthlyPaymentBuilder;
import com.management.gym.builders.StudentBuilder;
import com.management.gym.model.Student;
import com.management.gym.model.dto.MonthlyPaymentDTO;
import com.management.gym.service.MonthlyPaymentService;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = {MonthlyPaymentController.class})
@AutoConfigureWebMvc
@AutoConfigureMockMvc
class MonthlyPaymentControllerTest {

	@MockBean
	private MonthlyPaymentService monthlyPaymentService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objMapper;
	
	
	private Student student;
	private MonthlyPaymentDTO monthlyPaymentDTO;
	
	MonthlyPaymentBuilder monthlyPaymentBuilder = new MonthlyPaymentBuilder();
	StudentBuilder studentBuilder = new StudentBuilder();
	
	
	
	@BeforeEach
	private void setup() {
		MockitoAnnotations.openMocks(this);
		startStudentMock();
		startMonthlyPayment();
	}
	
	
	@Test
	void testListsAllStudentsWithStatusPayment() throws JsonProcessingException, Exception {
		Pageable pageable = PageRequest.of(0, 20);	
		var monthlyPaymentDTOPageMock = new PageImpl<>(List.of(monthlyPaymentDTO), pageable, pageable.getPageSize());

		when(monthlyPaymentService.findStudentsWithStatusPayment(student.getId(), 1, pageable)).thenReturn(monthlyPaymentDTOPageMock);
		
		mockMvc.perform(get("/monthly-payment/delayed-payments/b269ae57-31e2-44ec-bbb2-3b240a697337/1")
				.content(objMapper.writeValueAsBytes(monthlyPaymentDTOPageMock))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
		
	}

	@Test
	void testListsAllMonthlyPaymentLate() throws JsonProcessingException, Exception {
		Pageable pageable = PageRequest.of(0, 20);	
		var monthlyPaymentDTOPageMock = new PageImpl<>(List.of(monthlyPaymentDTO), pageable, pageable.getPageSize());

		when(monthlyPaymentService.findAllStudentsMonthlyPaymentLate(pageable)).thenReturn(monthlyPaymentDTOPageMock);
		
		mockMvc.perform(get("/monthly-payment/delayed-payments")
				.content(objMapper.writeValueAsBytes(monthlyPaymentDTOPageMock))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@SuppressWarnings("static-access")
	public void startStudentMock() {
		student = studentBuilder.createStudent();
	}
	
	@SuppressWarnings("static-access")
	public void startMonthlyPayment() {
		monthlyPaymentDTO = monthlyPaymentBuilder.createMonthlyPaymentDTO();
	}

}

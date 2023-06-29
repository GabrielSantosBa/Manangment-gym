//package com.management.gym.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import com.management.gym.builders.StudentBuilder;
//import com.management.gym.enums.FormOfPaymentEnum;
//import com.management.gym.model.Student;
//import com.management.gym.model.dto.MonthlyPaymentDTO;
//import com.management.gym.repository.MonthlyPaymentRepository;
//
//
//@ExtendWith(MockitoExtension.class)
//class MonthlyPaymentServiceTest {
//	
//	
//	@Mock
//	private MonthlyPaymentRepository monthlyPaymentRepository;
//
//	@InjectMocks
//	private MonthlyPaymentService monthlyPaymentService;
//	
//
//	private Student student;	
//	StudentBuilder studentBuilder = new StudentBuilder();	
//	
//	
//	@BeforeEach
//	public void setup() {
//		startStudentMock();
//	}
//	
//	
//	
//	@Test
//	void testFindStudentsWithStatusPayment() {
//		Pageable pageable =  PageRequest.of(0, 20);
//		PageImpl<MonthlyPaymentDTO> pageMock = new PageImpl<>(List.of(new MonthlyPaymentDTO()));
//		pageMock.getContent().get(0).setFormOfPayment(1);
//		
//		when(monthlyPaymentRepository.findAllByStatusById_student(student.getId(), 1, pageable)).thenReturn(pageMock);
//		var monthlyPaymentMock = monthlyPaymentRepository.findAllByStatusById_student(student.getId(), 1, pageable);
//		
//		monthlyPaymentService.findStudentsWithStatusPayment(student.getId(), FormOfPaymentEnum.PIX.getCode(), pageable);
//		
//		assertNotNull(monthlyPaymentMock);
//		assertEquals(FormOfPaymentEnum.PIX.getCode(), monthlyPaymentMock.getContent().get(0).getFormOfPayment().hashCode());
//		
//	}
//
//	@Test
//	void testFindAllStudentsMonthlyPaymentLate() {
//		
//		Pageable pageable =  PageRequest.of(0, 20);
//		PageImpl<MonthlyPaymentDTO> pageMock = new PageImpl<>(List.of(new MonthlyPaymentDTO()));
//		
//		when(monthlyPaymentService.findAllStudentsMonthlyPaymentLate(pageable)).thenReturn(pageMock);
//		var monthlyPaymentMock = monthlyPaymentRepository.findAllAccountsLatePayment(pageable);
//		
//		assertNotNull(monthlyPaymentMock);
//	}
//	
//	
//	@SuppressWarnings("static-access")
//	public void startStudentMock() {
//			student = studentBuilder.createStudent();
//	}
//
//}

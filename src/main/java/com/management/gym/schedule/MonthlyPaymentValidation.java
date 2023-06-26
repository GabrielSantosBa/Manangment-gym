package com.management.gym.schedule;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.management.gym.enums.FinancialStatusEnum;
import com.management.gym.model.DailyAccounting;
import com.management.gym.model.MonthlyPayment;
import com.management.gym.repository.DailyAccoutingRepository;
import com.management.gym.repository.MonthlyPaymentRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class MonthlyPaymentValidation {

	//private final StudentRepository studentRepository;
	private final MonthlyPaymentRepository monthlyPaymentRepository;
	
//	@Value("days.late.permited")
//	private Integer daysLatePermited; //TODO validar o uso de configurationProperties :: https://pt.stackoverflow.com/questions/334187/pegar-valor-de-uma-propriedade-do-application-properties-spring-boot
	
	private int daysLatePermited = 5;
	
	private final DailyAccoutingRepository dailyAccoutingRepository;
	
	
	@Scheduled(cron = "1 01 00 * * *", zone = "America/Sao_Paulo")
	private void validationExecute() {
		
		BigDecimal totalMonthlyDelayed = new BigDecimal(0);
		BigDecimal totalMonthlyOnDay = new BigDecimal(0);
		BigDecimal totalMonthlyDue = new BigDecimal(0);
		
		
		List<DailyAccounting> listDailyAccounting = new ArrayList<>();
		List<MonthlyPayment> listMonthlyPayment = new ArrayList<>();
		
		for (MonthlyPayment monthlyPayment : monthlyPaymentRepository.findAll()) {
			   
			Long daysLatePayment = monthlyPayment.getPayDay().until(LocalDate.now().plusDays(daysLatePermited), ChronoUnit.DAYS);
			
			if(daysLatePayment > 35) {
				monthlyPayment.setFinancialStatusEnum(FinancialStatusEnum.LATE);
				monthlyPayment.getStudent().setValuePaymentStatus(FinancialStatusEnum.LATE);
				monthlyPayment.setDaysLatePayment(daysLatePayment.intValue());
				totalMonthlyDelayed =  monthlyPayment.getMonthlyfee().add(totalMonthlyDelayed);
			} else if(daysLatePayment == 35) {
				totalMonthlyOnDay = monthlyPayment.getMonthlyfee().add(totalMonthlyOnDay);
				monthlyPayment.setFinancialStatusEnum(FinancialStatusEnum.IN_DAY);
				monthlyPayment.getStudent().setValuePaymentStatus(FinancialStatusEnum.IN_DAY);
			} else {
				totalMonthlyDue = monthlyPayment.getMonthlyfee().add(totalMonthlyDue);
				monthlyPayment.setFinancialStatusEnum(FinancialStatusEnum.TO_WIN);
				monthlyPayment.getStudent().setValuePaymentStatus(FinancialStatusEnum.TO_WIN);
			}
			listMonthlyPayment.add(monthlyPayment);
			listDailyAccounting.add(buildDailyAccounting(totalMonthlyDelayed, totalMonthlyOnDay, totalMonthlyDue));
		}
		
		dailyAccoutingRepository.saveAll(listDailyAccounting);//TODO erro aqui, validar o generatedValue
		monthlyPaymentRepository.saveAll(listMonthlyPayment);
	}


	private DailyAccounting buildDailyAccounting(BigDecimal totalMonthlyDelayed, BigDecimal totalMonthlyOnDay,
			BigDecimal totalMonthlyDue) {
		return DailyAccounting.builder()
		 						.accountingDate(LocalDate.now())
		 						.inDay(totalMonthlyOnDay)
		 						.late(totalMonthlyDelayed)
		 						.toWin(totalMonthlyDue)
		 					.build();
	}
}

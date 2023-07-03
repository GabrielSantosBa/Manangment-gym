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

	private final MonthlyPaymentRepository monthlyPaymentRepository;
	
	private final DailyAccoutingRepository dailyAccoutingRepository;
	
	private int daysLatePermited = 5;
	
	
	
	@Scheduled(cron = "1 01 00 * * *", zone = "America/Sao_Paulo")
	private void validationExecute() {
		
		BigDecimal totalMonthlyDelayed = new BigDecimal(0);
		BigDecimal totalMonthlyOnDay = new BigDecimal(0);
		BigDecimal totalMonthlyDue = new BigDecimal(0);
		
		
		List<DailyAccounting> listDailyAccounting = new ArrayList<>();
		List<MonthlyPayment> listMonthlyPayment = new ArrayList<>();
		
		
		for (MonthlyPayment monthlyPayment : monthlyPaymentRepository.findAll()) {

			Long daysLatePayment = monthlyPayment.getPaymentDate().until(LocalDate.now().plusDays(daysLatePermited),
					ChronoUnit.DAYS);

			if (daysLatePayment > 35) {
				monthlyPayment.setFinancialStatusEnum(FinancialStatusEnum.LATE);
				monthlyPayment.getStudent().setValuePaymentStatus(FinancialStatusEnum.LATE);
				monthlyPayment.setDaysLatePayment(daysLatePayment.intValue());
				totalMonthlyDelayed = monthlyPayment.getMonthlyfee().add(totalMonthlyDelayed);
			} else if (daysLatePayment == 35) {
				monthlyPayment.setFinancialStatusEnum(FinancialStatusEnum.IN_DAY);
				monthlyPayment.getStudent().setValuePaymentStatus(FinancialStatusEnum.IN_DAY);
				totalMonthlyOnDay = monthlyPayment.getMonthlyfee().add(totalMonthlyOnDay);
			} else {
				monthlyPayment.setFinancialStatusEnum(FinancialStatusEnum.TO_WIN);
				monthlyPayment.getStudent().setValuePaymentStatus(FinancialStatusEnum.TO_WIN);
				totalMonthlyDue = monthlyPayment.getMonthlyfee().add(totalMonthlyDue);
			}
			listMonthlyPayment.add(monthlyPayment);
			listDailyAccounting.add(buildDailyAccounting(totalMonthlyDelayed, totalMonthlyOnDay, totalMonthlyDue));
		}

		dailyAccoutingRepository.saveAll(listDailyAccounting);
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

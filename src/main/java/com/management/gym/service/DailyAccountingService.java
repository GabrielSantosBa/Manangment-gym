package com.management.gym.service;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.management.gym.common.Utilities;
import com.management.gym.model.DailyAccounting;
import com.management.gym.model.dto.DailyAccountingDTO;
import com.management.gym.repository.DailyAccoutingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DailyAccountingService {

	
	private final DailyAccoutingRepository dailyAccountingRepository;
	private final Utilities methodUtil;

	public List<DailyAccountingDTO> findAll() {
		
		List<DailyAccounting> listDailyAccounting = dailyAccountingRepository.findAll();
		
		if(ObjectUtils.isEmpty(listDailyAccounting)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
		
		return listDailyAccounting.stream().map(accounting -> (DailyAccountingDTO) methodUtil.convertTo(accounting, DailyAccountingDTO.class)).collect(toList());
		
	}
	
    public List<DailyAccountingDTO> findAllByPeriod(String initialPeriodo, String finalPeriodo) {
    	
    	//TODO validar se chegou tipo string
    	LocalDate initDay = LocalDate.parse(initialPeriodo);
    	LocalDate finalDay = LocalDate.parse(finalPeriodo);
    	
		List<DailyAccounting> listDailyAccounting = dailyAccountingRepository.findByAccountingDateBetween(initDay, finalDay);
		
		if(ObjectUtils.isEmpty(listDailyAccounting)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error");
		
		return listDailyAccounting.stream().map(accounting -> (DailyAccountingDTO) methodUtil.convertTo(accounting, DailyAccountingDTO.class)).collect(toList());
	}
	
}

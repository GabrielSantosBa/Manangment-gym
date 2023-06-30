package com.management.gym.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.model.dto.DailyAccountingDTO;
import com.management.gym.service.DailyAccountingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/daily-accounting")
@AllArgsConstructor
public class DailyAccountingController {

	private final DailyAccountingService dailyAccountingService;
	
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Operation error")
	})
	@Operation(summary = "List all Daily Accounting.", tags = {"DailyAccounting"})
	@GetMapping
	private ResponseEntity<List<DailyAccountingDTO>> findAllDailyAccounting() {
		return ResponseEntity.ok(dailyAccountingService.findAll());
	}
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Operation error, peridos invalido"),
			@ApiResponse(responseCode = "500", description = "Error intern")
	})
	@Operation(summary = "Lists all Accountings by a periodo.", tags = {"DailyAccounting"})
	@GetMapping("/by-period")
	private ResponseEntity<List<DailyAccountingDTO>> findAccountingByPeriod(@RequestParam("initalPeriodo") String initalPeriodo, @RequestParam("finalPeriodo") String finalPeriodo) {
		return ResponseEntity.ok(dailyAccountingService.findAllByPeriod(initalPeriodo, finalPeriodo));
	}
}

package com.management.gym.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.gym.model.dto.EmailDTO;
import com.management.gym.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RequestMapping("/email")
@RestController
public class EmailController {
	
	private final EmailService emailService;
	
	
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operation Success"),
			@ApiResponse(responseCode = "400", description = "Error")
	})
	@PostMapping
	@Operation(summary = "Send billing e-mail.", tags = {"Email"})
	public ResponseEntity<?> sendBillingEmail(@RequestBody EmailDTO emailDTO){
		emailService.send(emailDTO);
		return  ResponseEntity.ok().body("Email sending success!");
	}
	
}

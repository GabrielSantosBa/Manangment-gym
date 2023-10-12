package com.management.gym.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity(name = "tb_plan") 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Plan implements Serializable{

	
	private static final long serialVersionUID = 4489958219071898520L;
	
	@EqualsAndHashCode.Include	
	@Id 
	@GeneratedValue
	@Column(name = "id_plan")
	private Long id;	
	
	private String namePlan;	
	
	private BigDecimal valuePlan;
	
}

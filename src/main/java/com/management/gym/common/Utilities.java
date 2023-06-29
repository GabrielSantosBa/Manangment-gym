package com.management.gym.common;


import org.aspectj.weaver.ast.Instanceof;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import antlr.collections.List;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Utilities {

	private final ModelMapper modelMapper;
	
	public <T> Object convertTo(Object sourceObject, Class<T> classTarget) {
		return modelMapper.map(sourceObject, classTarget);
	}
	
}

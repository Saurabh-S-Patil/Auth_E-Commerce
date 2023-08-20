package com.sunbeaminfo;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // => consists of @Configuration+.....
//=> You can add bean configs here
public class Day14LabApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day14LabApplication.class, args);
	}

	@Bean // => a method level anno to tell SC , this method rets an
	// obj to be managed as spring bean by SC --configure ModelMapper as a spring
	// bean
	public ModelMapper modelMapper() {
		System.out.println("in model mapper bean method");
		ModelMapper mapper = new ModelMapper();
		// set mathching policy=STRICT =>the mapping of props will take place iff -->
		// names n data type of the props match
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}

}

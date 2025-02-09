package ru.sergey.moysklad.BasicRESTAPI;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BasicRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicRestapiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

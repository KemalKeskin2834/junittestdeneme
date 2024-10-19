package com.kemalkeskin.junittestdeneme;

import com.kemalkeskin.junittestdeneme.core.exception.BusinessException;
import com.kemalkeskin.junittestdeneme.core.exception.ProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@SpringBootApplication
@RestControllerAdvice
public class JunittestdenemeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunittestdenemeApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){ return new ModelMapper();}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ProblemDetails problemDetails(BusinessException businessException){
		ProblemDetails problemDetails=new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return problemDetails;
	}
}

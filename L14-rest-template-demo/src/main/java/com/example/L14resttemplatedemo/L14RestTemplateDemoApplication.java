package com.example.L14resttemplatedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class L14RestTemplateDemoApplication {

	//	We need to create the bean of RestTemplate before using it,
	//	only @Autowired doesn't work as RestTemplate class doesn't have @Component over it,
	//	so we are creating the bean explicitly here
	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}


	public static void main(String[] args) {
		SpringApplication.run(L14RestTemplateDemoApplication.class, args);
	}

}

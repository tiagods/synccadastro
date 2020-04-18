package com.prolink.synccadastro;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@RestController
@RequestMapping("/")
public class SynccadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SynccadastroApplication.class, args);
	}

	@GetMapping
	public String index(){
		return "redirect:api/aniversariantes";
	}
}

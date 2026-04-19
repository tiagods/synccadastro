package com.prolink.synccadastro.resources;

import com.prolink.synccadastro.services.AniversariantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prolink.synccadastro.services.ClientesServices;;

@RestController
@RequestMapping(value="/api/aniversariantes")
public class AniversariantesResources {
	@Autowired
	private AniversariantesService aniversariantesService;
	
	@GetMapping
	public ResponseEntity<?> index() {
		return ResponseEntity.status(HttpStatus.OK).body(aniversariantesService.getAniversariantes());
	}
}

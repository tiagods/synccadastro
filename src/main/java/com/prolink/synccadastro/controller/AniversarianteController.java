package com.prolink.synccadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.prolink.synccadastro.services.ClientesServices;

@Controller
@RequestMapping(value="/aniversariantes")
public class AniversarianteController {
	@Autowired
	private ClientesServices clientes;
	private String ANIVERSARIANTES= "Aniversarios";
	
	@RequestMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView(ANIVERSARIANTES);
		mv.addObject("aniversarios", clientes.getAniversariantes());
		return mv;
	}
}

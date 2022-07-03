package com.prolink.synccadastro.controller;

import com.prolink.synccadastro.services.AniversariantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/aniversariantes")
public class AniversarianteController {
	@Autowired
	private AniversariantesService aniversarianteService;
	private String ANIVERSARIANTES= "Aniversarios";
	
	@RequestMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView(ANIVERSARIANTES);
		mv.addObject("aniversarios", aniversarianteService.getAniversariantes());
		return mv;
	}
}

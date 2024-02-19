package org.edupoll.app.controller;

import org.edupoll.app.model.popular.Popular;
import org.edupoll.app.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JacksonException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MovieRepository movieRepository;
	
	
	
	@GetMapping({"/","/main"})
	public String showMainPage(Model model) throws JacksonException {
		Popular popular = movieRepository.findPopularList(1);
		
		model.addAttribute("popular", popular);
		
		
		return "/main";		
		
		
		
	}
		
		
	}



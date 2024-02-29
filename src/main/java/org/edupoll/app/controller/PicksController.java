package org.edupoll.app.controller;

import java.util.List;

import org.edupoll.app.entity.Picks;
import org.edupoll.app.repository.PicksRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/picks")
@RequiredArgsConstructor

public class PicksController {
	private final PicksRepository picksRepository;
	
	
	@GetMapping("/main")
	public String showAllPickList(Authentication authentication, Model model) {

		List<Picks> picksLists = picksRepository.findByAccountsUsername(authentication.getName());

		if(picksLists.isEmpty()) {
			model.addAttribute("message", "표시할 찜목록이 없습니다.");
		}
		
		model.addAttribute("picksLists", picksLists);

		return "/private/pick-list";

	}

}

package org.edupoll.app.controller;

import org.edupoll.app.command.Registration;
import org.edupoll.app.entity.Accounts;
import org.edupoll.app.repository.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
	private final AccountRepository accountRepository;

	@GetMapping("/register")
	public String showRegisterDisplay(Model model) {

		return "auth/register";

	}

	@PostMapping("/register")
	public String processRegisterHandler(Registration cmd) {

		if (accountRepository.findByUsername(cmd.getUsername()).isPresent()) {
			return "redirect:/auth/register?error";

		}
		
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		String encodedPassword = bCryptPasswordEncoder.encode(cmd.getPassword());
		
		

		Accounts one = Accounts.builder().username(cmd.getUsername()).userPassword("{bcrypt}"+ encodedPassword)
				.nickname(cmd.getNickname()).build();

		accountRepository.save(one);
		return "redirect:/main";

	}

	@GetMapping("/login")
	public String showLoginPage(Model model) {

		return "auth/login";

	}
	

}

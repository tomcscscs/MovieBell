package org.edupoll.app.controller;

import org.edupoll.app.entity.Accounts;
import org.edupoll.app.model.popular.MovieDetails;
import org.edupoll.app.model.popular.Popular;
import org.edupoll.app.repository.AccountRepository;
import org.edupoll.app.repository.MovieRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JacksonException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

	private final MovieRepository movieRepository;
	private final AccountRepository accountRepository;

	@GetMapping("/template")
	public String showTemplatePage(Model model) {

		return "/fragment/template";

	}

	@GetMapping({ "/", "/main" })
	public String showMainPage(Authentication authentication, Model model) throws JacksonException {
		Popular popular = movieRepository.findPopularList(1);
		Popular topRated = movieRepository.findTopRatedrList(1);

		if (authentication != null) {
			Accounts accounts = accountRepository.findByUsername(authentication.getName()).get();

			model.addAttribute("accounts", accounts);
		}
		model.addAttribute("popular", popular);
		model.addAttribute("topRated", topRated);

		return "/main";

	}

	@GetMapping("/movie/{movieId}")
	public String showDetailPage(@PathVariable Integer movieId, Model model) throws JacksonException {
		// 추후 세션추가.
		MovieDetails details = movieRepository.findMovieDetailsById(movieId);

		model.addAttribute("details", details);

		return "movie/details";

	}

	@GetMapping("/movie/popularAll")
	public String showPopularAllPage(@RequestParam(defaultValue = "1") int page, Model model) throws JacksonException {
	    Popular popular = movieRepository.findPopularList(page);
	    model.addAttribute("popular", popular);
	    return "movie/popular-all";
	}


	@GetMapping("/movie/topratedAll")
	public String showTopratedAllPage(@RequestParam(defaultValue = "1") int page, Model model) throws JacksonException {
		Popular topRated = movieRepository.findTopRatedrList(page);
		model.addAttribute("toprated", topRated);


		return "movie/toprated-all";

	}

}

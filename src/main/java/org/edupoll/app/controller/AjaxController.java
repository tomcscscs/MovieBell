package org.edupoll.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.edupoll.app.command.PicksCommand;
import org.edupoll.app.entity.Picks;
import org.edupoll.app.repository.AccountRepository;
import org.edupoll.app.repository.PicksRepository;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AjaxController {

	private final AccountRepository accountRepository;
	private final PicksRepository picksRepository;

	@PostMapping("/ajax/pickAjax")
	@ResponseBody
	public Map<String, String> processPickController(@RequestBody PicksCommand cmd, Authentication authentication,
			Model model) {
		// Picks 객체 생성 및 저장
		Picks one = Picks.builder().movieId(cmd.getMovieId()).originalTitle(cmd.getMovieTitle())
				.releaseDate(cmd.getTiming()).posterPath(cmd.getPosterPath())
				.accounts(accountRepository.findByUsername(authentication.getName()).orElse(null)).build();

		picksRepository.save(one);

		// 클라이언트에게 성공적인 응답 반환
		Map<String, String> response = new HashMap<>();
		response.put("result", "success");

		return response;
	}
}

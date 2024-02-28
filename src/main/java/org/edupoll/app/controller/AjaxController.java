package org.edupoll.app.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.edupoll.app.command.PicksCommand;
import org.edupoll.app.entity.Accounts;
import org.edupoll.app.entity.Picks;
import org.edupoll.app.repository.AccountRepository;
import org.edupoll.app.repository.PicksRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
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
	public Map<String, String> processPickController(PicksCommand cmd, Authentication authentication) {
		Map<String, String> response = new LinkedHashMap<>();
		if (authentication == null) {
			response.put("result", "error");
			return response;
		}
		// System.out.println(cmd);
		String userName = authentication.getName();
		if (cmd.getPicked()) {
			// save
			Accounts account = accountRepository.findByUsername(userName).get();
			Picks entity = Picks.builder().accounts(account).movieId(cmd.getMovieId())
					.originalTitle(cmd.getMovieTitle()).releaseDate(cmd.getReleaseDate())
					.posterPath(cmd.getPosterPath()).build();
			picksRepository.save(entity);
			response.put("result", "success");
			return response;
		} else {
			// delete
			Optional<Picks> optional =
					picksRepository.findByAccountsUsernameAndMovieId(userName, cmd.getMovieId());
			if(optional.isEmpty()) {
				response.put("result", "error");
				return response;
			}
			Picks entity = optional.get();
			picksRepository.delete(entity);
			response.put("result", "success");
			return response;
		}
	}
}

package org.edupoll.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.edupoll.app.command.AddPost;
import org.edupoll.app.command.UpdatePost;
import org.edupoll.app.entity.Posts;
import org.edupoll.app.repository.PostRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

	private final PostRepository postRepository;

	@GetMapping("/main")
	public String showCommunityPage(Model model) {
		List<Posts> postsaAll = postRepository.findAll();
		model.addAttribute("postsAll", postsaAll);

		return "/community";

	}

	@GetMapping("/postmain")
	public String showPostWritePage(Model model) {

		return "/private/post-write";

	}

	@PostMapping("/savepost")
	public String savingPost(Authentication authentication, AddPost cmd) {

		LocalDateTime currentTime = LocalDateTime.now();

		Posts one = Posts.builder().//
				userId(authentication.getName()).//
				title(cmd.getTitle()).//
				contents(cmd.getContent()).//
				writeAt(currentTime).build();

		postRepository.save(one);

		return "redirect:/community/main";

	}

	@GetMapping("/details")
	public String detailPost(@RequestParam(required = false) Integer postId, Model model) {
		Posts details = postRepository.findById(postId).get();
		model.addAttribute("details", details);

		return "/community/details";

	}

	@GetMapping("/updatepost")
	public String showPostUpdatePage(@RequestParam(required = false) Integer postId, Model model) {

		Posts tempPosts = postRepository.findById(postId).get();

		model.addAttribute("tempPosts", tempPosts);

		return "/private/post-update";

	}

	@PostMapping("/updatepost")
	public String processUpdatePostHandler(Authentication authentication,UpdatePost cmd, Model model) {

		LocalDateTime currentTime = LocalDateTime.now();

		Posts one = Posts.builder().id(cmd.getPostId()).userId(authentication.getName()).title(cmd.getTitle()).contents(cmd.getContent())
				.writeAt(currentTime).build();

		postRepository.save(one);

		return "redirect:/community/main";

	}

}

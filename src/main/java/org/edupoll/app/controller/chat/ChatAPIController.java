package org.edupoll.app.controller.chat;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.edupoll.app.command.chat.AddMessage;
import org.edupoll.app.entity.chat.Messages;
import org.edupoll.app.repository.chat.MessageRepository;
import org.edupoll.app.repository.chat.RoomRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chat/api")
@RequiredArgsConstructor

public class ChatAPIController {
	private final SimpMessagingTemplate messageTemplate;
	private final MessageRepository messageRepository;
	private final RoomRepository roomRepository;

	@PostMapping("/{roomId}")
	public Map<?, ?> handleAddMessage(@PathVariable String roomId, AddMessage cmd) {
		System.out.println(cmd);

		LocalDateTime current = LocalDateTime.now();

		Messages entity = Messages.builder().room(roomRepository.findById(roomId).get()).//
				body(cmd.getBody()).//
				createdAt(current).//
				build();

		messageRepository.save(entity);

		var map = new LinkedHashMap<>();
		map.put("result", true);

		var payload = new LinkedHashMap<>();
		payload.put("type", "newMessage");
		payload.put("data", entity);

		messageTemplate.convertAndSend("/chat/" + roomId, payload);

		return map;

	}

}

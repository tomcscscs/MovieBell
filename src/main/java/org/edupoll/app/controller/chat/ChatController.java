package org.edupoll.app.controller.chat;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.edupoll.app.entity.chat.Messages;
import org.edupoll.app.entity.chat.Rooms;
import org.edupoll.app.repository.chat.MessageRepository;
import org.edupoll.app.repository.chat.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/chat")
@RequiredArgsConstructor

public class ChatController {

	private final SimpMessagingTemplate messagingTemplate;
	private final RoomRepository roomRepository;
	private final MessageRepository messageRepository;

	@GetMapping("/rooms")
	public String showAllChatRooms(@RequestParam(defaultValue = "1") int p, Model model) {

		Page<Rooms> page = roomRepository.findAll(PageRequest.of(p - 1, 10));
		List<Rooms> rooms = page.getContent();

		model.addAttribute("rooms", rooms);
		model.addAttribute("totalPage", page.getTotalPages());
		model.addAttribute("totalElements", page.getTotalElements());

		return "chat/chatroom-list";

	}
	
	@GetMapping("/make/{roomId}")
	public String showSpecificChatRoom(@PathVariable String roomId, Model model) {
			Rooms room =roomRepository.findById(roomId).get();
			List<Messages> messages =messageRepository.findByRoomsOrderByCreatedAtAsc(room);
			
			model.addAttribute("room", room);
			model.addAttribute("messages", messages);
			
			return "chat/chatroom-view";
	}
	
	@GetMapping("/make/room")
	public String proceedMakeChatRoom(Model model) {
		
		String id = UUID.randomUUID().toString().split("-")[0];
		Map<String, Object> body =new LinkedHashMap<>();
		body.put("type", "newRoom");
		body.put("roomId", id);
		
		messagingTemplate.convertAndSend("/chat", body);
		
		return "redirect:/chat/room/3";
		
		
	}
			
			
			
		
		
		

}

package org.edupoll.app.repository.chat;

import java.util.List;

import org.edupoll.app.entity.chat.Messages;
import org.edupoll.app.entity.chat.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Messages, Long> {
	
	List<Messages> findByRoomsOrderByCreatedAtAsc(Rooms room);
	
	
	
	
}
	


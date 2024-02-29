package org.edupoll.app.repository.chat;

import org.edupoll.app.entity.chat.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Rooms, String> {
	
	

}

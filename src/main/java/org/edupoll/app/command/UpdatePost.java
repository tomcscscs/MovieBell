package org.edupoll.app.command;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UpdatePost {
	
	private String title;
	
	private String content;
	
	private Integer postId;
	
	private LocalDateTime updatedAt;
	

}

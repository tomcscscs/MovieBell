package org.edupoll.app.command;

import java.time.LocalDate;

import lombok.Data;

@Data

public class PicksCommand {
	
	private Integer movieId;

	private String movieTitle;

	private LocalDate timing;

	private String posterPath;
	
	

}

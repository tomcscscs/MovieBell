package org.edupoll.app.command;

import java.time.LocalDate;

import lombok.Data;

@Data

public class PicksCommand {
	private Boolean picked;
	private Integer movieId;

	private String movieTitle;

	private LocalDate releaseDate;

	private String posterPath;

}

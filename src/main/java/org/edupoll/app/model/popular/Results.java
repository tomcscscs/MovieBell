package org.edupoll.app.model.popular;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class Results {

	private Boolean adult;

	@JsonProperty("backdrop_path")
	private String backdropPath;

	@JsonProperty("genre_ids")
	private List<Integer> genreIds;

	private Integer id;

	@JsonProperty("original_title")
	private String originalTitle;

	private String overview;
	private Double popularity;

	@JsonProperty("poster_path")
	private String posterPath;

	@JsonProperty("release_date")
	private Date releaseDate;

	private String title;
	private Boolean video;

	@JsonProperty("vote_average")
	private Double voteAverage;

	@JsonProperty("vote_count")
	private Integer voteCount;

}

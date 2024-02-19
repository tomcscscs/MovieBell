package org.edupoll.app.model.popular;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class Popular {

	private Long page;
	
	private List<Results> results;

	@JsonProperty("total_pages")
	private Long totalPages;

	@JsonProperty("total_results")
	private Long totalResults;

}

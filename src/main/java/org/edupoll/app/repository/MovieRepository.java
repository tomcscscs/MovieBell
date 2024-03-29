package org.edupoll.app.repository;

import org.edupoll.app.model.popular.Popular;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Repository

public class MovieRepository {
	
	public Popular findPopularList(int page) throws JsonMappingException, JsonProcessingException {
		
		RestTemplate template = new RestTemplate();

		String url = "https://api.themoviedb.org/3/movie/popular?";
		url += "api_key=e24068e30b82431ee5e3a68d4e195cf3&";
		url += "language=ko-KR&";
		url += "page=" + page;

		ResponseEntity<Popular> response = template.exchange(url, HttpMethod.GET, null, Popular.class);

		return response.getBody();

		/*
		 * ResponseEntity<String> response = template.exchange(url, HttpMethod.GET,
		 * null, String.class);
		 * 
		 * String resposneBody = response.getBody();
		 * 
		 * ObjectMapper mapper = new ObjectMapper(); return
		 * mapper.readValue(resposneBody, MovieList.class);
		 */
	}
	
	

}

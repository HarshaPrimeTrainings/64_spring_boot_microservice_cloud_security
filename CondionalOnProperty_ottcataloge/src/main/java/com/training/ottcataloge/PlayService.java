package com.training.ottcataloge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PlayService {
	
	
	@Autowired
	MovieService movieService;
	
	public List<Movie> playMovies(){
		return movieService.getMovies();
	}

}

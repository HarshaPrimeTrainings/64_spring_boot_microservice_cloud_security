package com.training.ottcataloge;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface MovieService {
	
	public List<Movie> getMovies();

}

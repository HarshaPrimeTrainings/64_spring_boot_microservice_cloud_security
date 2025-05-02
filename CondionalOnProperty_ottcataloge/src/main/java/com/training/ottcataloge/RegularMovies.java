package com.training.ottcataloge;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "prime.user" ,havingValue = "false")
@Component
public class RegularMovies implements MovieService{

	@Override
	public List<Movie> getMovies() {
		return List.of(new Movie("Avengers","non-prime","5"),new Movie("Spriderman","non-prime","5"),
				new Movie("supermane","non-prime","4"));
	}

}

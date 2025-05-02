package com.training.ottcataloge;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(name = "prime.user" ,havingValue = "true")
@Component("primemovies")
public class PrimeMovies implements MovieService{

	@Override
	public List<Movie> getMovies() {
		return List.of(new Movie("openhammer","prime","5"),new Movie("interstaller","prime","5"),
				new Movie("Into-The-Wild","prime","5"));
	}

}

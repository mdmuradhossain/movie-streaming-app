package io.murad.movie.streaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.murad.movie.streaming.model.Movie;
import io.murad.movie.streaming.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public Movie uploadMovie(Movie movie) {
		return movieRepository.save(movie);
	}
}

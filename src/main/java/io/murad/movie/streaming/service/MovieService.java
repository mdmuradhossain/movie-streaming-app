package io.murad.movie.streaming.service;

import java.util.List;


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

	public List<Movie> getMovies() {
		return movieRepository.findAll();
	}
	
	public Movie getMovie(Long id) {
		return movieRepository.findById(id).get();
	}
}

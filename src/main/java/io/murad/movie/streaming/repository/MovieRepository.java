package io.murad.movie.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.murad.movie.streaming.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}

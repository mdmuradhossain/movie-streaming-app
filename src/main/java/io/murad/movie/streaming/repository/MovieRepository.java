package io.murad.movie.streaming.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.murad.movie.streaming.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	List<Movie> findByCategoryId(Long categoryId);
}

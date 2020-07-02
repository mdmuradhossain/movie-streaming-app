package io.murad.movie.streaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.murad.movie.streaming.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}

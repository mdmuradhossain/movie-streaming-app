package io.murad.movie.streaming.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.murad.movie.streaming.model.Category;
import io.murad.movie.streaming.model.Movie;
import io.murad.movie.streaming.service.CategoryService;
import io.murad.movie.streaming.service.MovieService;

@Controller()
@RequestMapping("hd")
public class FrontController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/categories")
	public String getCategories(Model model) {
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		return "front/categories";
	}
	
	@GetMapping("/movies")
	public String movies(Model model) {
		List<Movie> movies = movieService.getMovies();
		model.addAttribute("movies",movies);
		return "front/movies";
	}
	
	@GetMapping("/show/{id}")
	public String showMovie(@PathVariable("id") Long id,Model model) {
		Movie movie = movieService.getMovie(id);
		model.addAttribute("movie",movie);
		return "front/showMovie";
	}
	
	@GetMapping("/category/{categoryId}")
	public String getAllMoviesByCategory(@PathVariable("categoryId") Long categoryId,Model model) {
		List<Movie> categoryMovies = movieService.findMoviesByCategory(categoryId);
		model.addAttribute("movies",categoryMovies);
		return "front/moviesByCategory";
	}
}

package io.murad.movie.streaming.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.murad.movie.streaming.model.Category;
import io.murad.movie.streaming.model.Movie;
import io.murad.movie.streaming.service.CategoryService;
import io.murad.movie.streaming.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	private List<String> imdbRatings = new ArrayList<>();
	
	private CategoryService categoryService;
	private MovieService movieService;

	@Autowired
	public MovieController(CategoryService categoryService, MovieService movieService) {
		this.categoryService = categoryService;
		this.movieService = movieService;
	}

	@RequestMapping("/showMovieForm")
	public String showMovieForm(Model model) {
		Movie movie = new Movie();
		model.addAttribute("movie", movie);
		model.addAttribute("categories", categoryService.getCategories());
		
		for (float i = 1; i <=10; i=(float) (i+0.1)) {
			BigDecimal imdbRating = new BigDecimal(i);
			imdbRating = imdbRating.setScale(1,RoundingMode.HALF_UP).abs();
			//System.out.println(imdbRating.doubleValue());
			imdbRatings.add(imdbRating.toString());
		}
		model.addAttribute("imdbRating",imdbRatings);
		return "admin/movie/movieForm";
	}

	@PostMapping("/uploadMovie")
	public String uploadMovie(@ModelAttribute("movie") Movie movie,
			@RequestParam("featured") MultipartFile multipartFile, @RequestParam("film") MultipartFile multipartFile2,
			Model model, RedirectAttributes redirectAttributes, @RequestParam("categoryId") Long categoryId)
			throws IOException {
		Category category = categoryService.getCategory(categoryId);
		movie.setCategory(category);
		String imageName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String filmName = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
		System.out.println(multipartFile.getContentType());
		movie.setThumbnail(imageName);
		movie.setVideo(filmName);
		movieService.uploadMovie(movie);
		Path uploadPath = Paths.get("uploads/");
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path imagePath = uploadPath.resolve(imageName);
			System.out.println(imagePath.toFile().getAbsolutePath());
			Files.copy(inputStream, imagePath, StandardCopyOption.REPLACE_EXISTING);
		}

		try (InputStream inputStream2 = multipartFile2.getInputStream()) {
			Path filmPath = uploadPath.resolve(filmName);
			System.out.println(filmPath.toFile().getAbsolutePath());
			Files.copy(inputStream2, filmPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("could not save");
		}
		/**
		 * String thumbnailUploadDir =
		 * request.getServletContext().getRealPath(imageUploadFolder); String
		 * thumbnailName = multipartFile.getOriginalFilename(); String thumbnailPath =
		 * Paths.get(thumbnailUploadDir, thumbnailName).toString(); try { File dir = new
		 * File(thumbnailUploadDir); if (!dir.exists()) { dir.mkdirs(); }
		 * BufferedOutputStream stream = new BufferedOutputStream(new
		 * FileOutputStream(new File(thumbnailPath)));
		 * stream.write(multipartFile.getBytes()); stream.close(); } catch (Exception e)
		 * { // TODO: handle exception e.printStackTrace(); } byte[] thumbnailData =
		 * multipartFile.getBytes(); movie.setThumbnail(thumbnailName);
		 * movie.setImageFile(thumbnailData); ; movieService.uploadMovie(movie);
		 */
		redirectAttributes.addFlashAttribute("msg", "movie saved");
		return "admin/movie/movieList";
	}

	@GetMapping("/displayMovies")
	public String displayMovies(Model model) {
		List<Movie> movies = movieService.getMovies();
		model.addAttribute("movies", movies);
		return "admin/movie/movieList";
	}
	

}

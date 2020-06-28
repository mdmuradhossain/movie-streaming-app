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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.murad.movie.streaming.model.Category;
import io.murad.movie.streaming.model.Movie;
import io.murad.movie.streaming.service.CategoryService;
import io.murad.movie.streaming.service.MovieService;

@Controller
@RequestMapping("movie")
public class MovieController {

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
//		for (float i = 1; i <=10; i=(float) (i+0.1)) {
//			BigDecimal imdbRating = new BigDecimal(i);
//			imdbRating = imdbRating.setScale(1,RoundingMode.HALF_UP).abs();
//		}
//	
//		model.addAttribute("imgRating",imdbRating);
		return "admin/movie/movieForm";
	}

	@PostMapping("/uploadMovie")
	public String uploadMovie(@ModelAttribute("movie") Movie movie,
			@RequestParam("thumbnail") MultipartFile multipartFile, Model model, RedirectAttributes redirectAttributes,
			@RequestParam("categoryId") Long categoryId, HttpServletRequest request) throws IOException {
		Category category = categoryService.getCategory(categoryId);
		movie.setCategory(category);
		String imageName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		movie.setThumbnail(imageName);
		movieService.uploadMovie(movie);
//		String uploadDir = "/images/"+savedMovie.getId();
		Path uploadPath = Paths.get("uploads/");
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		
		try(InputStream inputStream = multipartFile.getInputStream()){
		Path imagePath = uploadPath.resolve(imageName);
		Files.copy(inputStream, imagePath,StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException e) {
		throw new IOException("could not save");
		}
/**		String thumbnailUploadDir = request.getServletContext().getRealPath(imageUploadFolder);
		String thumbnailName = multipartFile.getOriginalFilename();
		String thumbnailPath = Paths.get(thumbnailUploadDir, thumbnailName).toString();
		try {
			File dir = new File(thumbnailUploadDir);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(thumbnailPath)));
			stream.write(multipartFile.getBytes());
			stream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		byte[] thumbnailData = multipartFile.getBytes();
		movie.setThumbnail(thumbnailName);
		movie.setImageFile(thumbnailData);
		;
		movieService.uploadMovie(movie);
		*/
		redirectAttributes.addFlashAttribute("msg", "movie saved");
		return "admin/movie/movieList";
	}
}

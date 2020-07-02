package io.murad.movie.streaming.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.murad.movie.streaming.model.Category;
import io.murad.movie.streaming.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getCategories(){
		return categoryRepository.findAll();
	}
	
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public Category getCategory(Long categoryId) {
		return categoryRepository.getOne(categoryId);
	}
	
	
}

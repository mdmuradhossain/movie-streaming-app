package io.murad.movie.streaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.murad.movie.streaming.model.Category;
import io.murad.movie.streaming.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/showCategoryForm")
	public String showCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category",category);
		return "admin/category/categoryForm";
	}
	
	@PostMapping("/addCategory")
	public String addCategory(@ModelAttribute("category") Category category) {
		categoryService.saveCategory(category);
		return "admin/category/categoryList";
	}
	
	@GetMapping("/displayCategory")
	public String displayCategory(Model model) {
		List<Category> categories = categoryService.getCategories();
		model.addAttribute("categories", categories);
		return "admin/category/categoryList";
	}
	
	
}

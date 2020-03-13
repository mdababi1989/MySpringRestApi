package com.mdababi.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mdababi.api.v1.model.CategoryDTO;
import com.mdababi.api.v1.model.CategoryListDTO;
import com.mdababi.service.CategoryService;

@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<CategoryListDTO> listCategories() {
		return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getAllCategories()),
				HttpStatus.OK);
	}
	@GetMapping("{name}")
	public ResponseEntity<CategoryDTO> getByNameCategories(@PathVariable String name) {
		return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByName(name), HttpStatus.OK);
	}

}

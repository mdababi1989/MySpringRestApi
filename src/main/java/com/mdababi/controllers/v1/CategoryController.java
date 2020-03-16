package com.mdababi.controllers.v1;

import com.mdababi.api.v1.model.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.mdababi.api.v1.model.CategoryDTO;
import com.mdababi.api.v1.model.CategoryListDTO;
import com.mdababi.service.CategoryService;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {
public static final String BASE_URL = "/api/v1/categories/";

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryListDTO listCategories() {
		return new CategoryListDTO(categoryService.getAllCategories());
	}
	@GetMapping("{name}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryDTO getByNameCategories(@PathVariable String name) {
		return categoryService.getCategoryByName(name);
	}

}

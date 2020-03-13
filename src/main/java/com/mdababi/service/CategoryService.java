package com.mdababi.service;

import java.util.List;

import com.mdababi.api.model.CategoryDTO;

public interface CategoryService {
	
	List<CategoryDTO> getAllCategories();
	CategoryDTO getCategoryByName(String name);

}

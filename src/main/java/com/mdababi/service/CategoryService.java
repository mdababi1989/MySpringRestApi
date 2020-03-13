package com.mdababi.service;

import java.util.List;

import com.mdababi.api.v1.model.CategoryDTO;

public interface CategoryService {
	
	List<CategoryDTO> getAllCategories();
	CategoryDTO getCategoryByName(String name);

}

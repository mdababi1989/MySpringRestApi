package com.mdababi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mdababi.api.mapper.CategoryMapper;
import com.mdababi.api.model.CategoryDTO;
import com.mdababi.domain.Category;
import com.mdababi.repositories.CategoryRepository;

class CategoryServiceTest {
	private static final long ID = 2L;
	private static final String NAME = "Jimmy";
	CategoryService categoryService;
	@Mock
	CategoryRepository categoryRepository;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
	}

	@Test
	void testGetAllCategories() throws Exception{
		List<Category> categories = Arrays.asList(new Category(),new Category(),new Category());
		when(categoryRepository.findAll()).thenReturn(categories);
		List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();
		assertEquals(3, categoryDTOs.size());
	}

	@Test
	void testGetCategoryByName() {
		Category category = Category.builder().name(NAME).id(ID).build();
		when(categoryRepository.getCategoryByName(anyString())).thenReturn(category);
		
		CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);
		
		assertEquals(ID, categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
			
	}

}

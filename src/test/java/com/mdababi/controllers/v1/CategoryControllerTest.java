package com.mdababi.controllers.v1;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.mdababi.service.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.mdababi.api.v1.model.CategoryDTO;
import com.mdababi.service.CategoryService;

class CategoryControllerTest {
	public static final String NAME = "Jim";

	@Mock
	CategoryService categoryService;
	@InjectMocks
	CategoryController categoryController;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).setControllerAdvice(new RestResponseEntityExceptionHandler()).build();
	}

	@Test
	void testListCategories() throws Exception {
		CategoryDTO category1 = CategoryDTO.builder().id(1L).name(NAME).build();
		CategoryDTO category2 = CategoryDTO.builder().id(2L).name("Bob").build();
		List<CategoryDTO> categories = Arrays.asList(category1, category2);

		when(categoryService.getAllCategories()).thenReturn(categories);

		mockMvc.perform(get(CategoryController.BASE_URL).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.categories", hasSize(2)));
	}

	@Test
	void testGetByNameCategories() throws Exception {
		CategoryDTO category1 = CategoryDTO.builder().id(1L).name(NAME).build();
		when(categoryService.getCategoryByName(anyString())).thenReturn(category1);
		mockMvc.perform(get(CategoryController.BASE_URL + NAME).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name", equalTo(NAME)));
	}

	@Test
	void testGetByNameNotFound() throws Exception {
		when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);
		mockMvc.perform(get(CategoryController.BASE_URL + "Test").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

}

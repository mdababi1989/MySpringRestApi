package com.mdababi.api.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.mdababi.api.model.CategoryDTO;
import com.mdababi.domain.Category;

class CategoryMapperTest {
	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

	@Test
	void categoryToCategoryDTO()  throws Exception{
		Category category = new Category();
		category.setName("Joe");
		category.setId(1L);
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		assertEquals(Long.valueOf(1L), categoryDTO.getId());
		assertEquals("Joe", categoryDTO.getName());
	}

}

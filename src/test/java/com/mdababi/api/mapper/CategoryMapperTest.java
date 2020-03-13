package com.mdababi.api.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.mdababi.api.v1.mapper.CategoryMapper;
import com.mdababi.api.v1.model.CategoryDTO;
import com.mdababi.domain.Category;

class CategoryMapperTest {
	private static final long ID = 1L;
	private static final String NAME = "Joe";
	CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

	@Test
	void categoryToCategoryDTO()  throws Exception{
		Category category = Category.builder().name(NAME).id(ID).build();
		CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
		assertEquals(Long.valueOf(1L), categoryDTO.getId());
		assertEquals(NAME, categoryDTO.getName());
	}

}

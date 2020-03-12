package com.mdababi.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mdababi.api.model.CategoryDTO;
import com.mdababi.domain.Category;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	CategoryDTO categoryToCategoryDTO(Category category);
	
	
}

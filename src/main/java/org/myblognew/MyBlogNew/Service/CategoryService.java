package org.myblognew.MyBlogNew.Service;

import org.myblognew.MyBlogNew.dto.CategoryDTO;
import org.myblognew.MyBlogNew.exception.ResourceNotFoundException;
import org.myblognew.MyBlogNew.mapper.CategoryMapper;
import org.myblognew.MyBlogNew.model.Category;
import org.myblognew.MyBlogNew.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.convertToDTO(savedCategory);
    }


    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::convertToDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            throw new ResourceNotFoundException("Category not found with id " + id);
        }
        return categoryMapper.convertToDTO(optionalCategory.get());
    }


    public Optional<CategoryDTO> updateCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return Optional.empty();
        }
        Category category = optionalCategory.get();
        category.setName(categoryDTO.getName());

        Category updateCategory = categoryRepository.save(category);
        return Optional.of(categoryMapper.convertToDTO(updateCategory));

    }

    public boolean deleteCategory(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return false;
        }
        categoryRepository.delete(optionalCategory.get());
        return true;
    }

}




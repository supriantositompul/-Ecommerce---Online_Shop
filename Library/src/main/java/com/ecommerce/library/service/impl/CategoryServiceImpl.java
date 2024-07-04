package com.ecommerce.library.service.impl;

import com.ecommerce.library.dto.CategoryDto;
import com.ecommerce.library.model.Category;
import com.ecommerce.library.repository.CategoryRepository;
import com.ecommerce.library.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        Category categorySave = new Category();
        categorySave.setName(category.getName());
        categorySave.setActivated(true);
        categorySave.setDeleted(false);
        return categoryRepository.save(categorySave);
    }

    @Override
    @Transactional
    public Category update(Category category) {
        Category categoryUpdate = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + category.getId()));
        categoryUpdate.setName(category.getName());
        return categoryRepository.save(categoryUpdate);
    }

    @Override
    public List<Category> findAllByActivatedTrue() {
        return categoryRepository.findAllByActivatedTrue();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
        category.setActivated(false);
        category.setDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void enableById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
        category.setActivated(true);
        category.setDeleted(false);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> getCategoriesAndSize() {
        return categoryRepository.getCategoriesAndSize();
    }
}

package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Optional<Category> findById(long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public void update(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> findAllWhereVisibleIsTrue() {
        return categoryRepository.findAllByVisibleIsTrue();
    }
}

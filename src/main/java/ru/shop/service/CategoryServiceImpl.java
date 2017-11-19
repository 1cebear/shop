package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.shop.model.Category;
import ru.shop.repository.CategoryRepository;
import ru.shop.util.exception.NotFoundException;

import java.util.List;

import static ru.shop.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category save(Category category) {
        Assert.notNull(category, "category must not be null");
        return repository.save(category);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Category get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Category> getAll() {
        return repository.getAll();
    }

    public void update(Category order) {
        Assert.notNull(order, "category must not be null");
        repository.save(order);
    }
}

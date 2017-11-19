package ru.shop.repository;

import ru.shop.model.Category;

import java.util.List;

public interface CategoryRepository {
    Category save(Category category);

    // false if not found
    boolean delete(int id);

    // null if not found
    Category get(int id);

    List<Category> getAll();
}

package ru.shop.service;

import ru.shop.model.Category;
import ru.shop.util.exception.NotFoundException;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    void delete(int id) throws NotFoundException;

    Category get(int id) throws NotFoundException;

    List<Category> getAll();

    void update(Category order);
}

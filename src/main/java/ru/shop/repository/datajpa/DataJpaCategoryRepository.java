package ru.shop.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.Category;
import ru.shop.repository.CategoryRepository;

import java.util.List;

@Repository
public class DataJpaCategoryRepository implements CategoryRepository{

    @Autowired
    private CrudCategoryRepository crudRepository;

    @Transactional
    public Category save(Category category) {
        return crudRepository.save(category);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public Category get(int id) {
        return crudRepository.findOne(id);
    }

    public List<Category> getAll() {
        return crudRepository.findAll();
    }
}

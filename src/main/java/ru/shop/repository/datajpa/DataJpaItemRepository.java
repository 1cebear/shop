package ru.shop.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shop.model.Item;
import ru.shop.repository.ItemRepository;

import java.util.List;

@Repository
public class DataJpaItemRepository implements ItemRepository {

    @Autowired
    private CrudItemRepository crudRepository;


    public Item save(Item user) {
        return crudRepository.save(user);
    }


    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }


    public Item get(int id) {
        return crudRepository.findOne(id);
    }


    public List<Item> getAll() {
        return crudRepository.findAll();
    }
}

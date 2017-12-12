package ru.shop.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.Item;
import ru.shop.repository.ItemRepository;

import java.util.List;

@Repository
public class DataJpaItemRepository implements ItemRepository {

    @Autowired
    private CrudItemRepository crudItemRepository;

    @Autowired
    private CrudCategoryRepository crudCategoryRepository;

    @Transactional
    public Item save(Item item, int categoryId) {
        if (!item.isNew() && get(item.getId(), categoryId) == null)
            return null;
        item.setCategory(crudCategoryRepository.findOne(categoryId));
        return crudItemRepository.save(item);
    }


    public boolean delete(int id, int categoryId) {
        return crudItemRepository.delete(id, categoryId) != 0;
    }


    public Item get(int id, int categoryId) {
        Item item = crudItemRepository.findOne(id);
        return item != null && item.getCategory().getId() == categoryId ? item : null;
    }


    public List<Item> getAll(int categoryId) {
        return crudItemRepository.getAll(categoryId);
    }
}

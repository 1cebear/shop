package ru.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.shop.model.Item;
import ru.shop.repository.ItemRepository;
import ru.shop.util.exception.NotFoundException;

import java.util.List;

import static ru.shop.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    public Item save(Item item, int categoryId) {
        Assert.notNull(item, "item must not be null");
        return repository.save(item, categoryId);
    }

    public void delete(int id, int categoryId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, categoryId), id);
    }

    public Item get(int id, int categoryId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, categoryId), id);
    }

    public List<Item> getAll(int categoryId) {
        return repository.getAll(categoryId);
    }

    public void update(Item item, int categoryId) {
        Assert.notNull(item, "item must not be null");
        repository.save(item, categoryId);
    }
}

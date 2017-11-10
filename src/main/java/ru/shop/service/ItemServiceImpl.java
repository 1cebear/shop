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

    public Item save(Item item) {
        Assert.notNull(item, "item must not be null");
        return repository.save(item);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Item get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Item> getAll() {
        return repository.getAll();
    }

    public void update(Item item) {
        Assert.notNull(item, "item must not be null");
        repository.save(item);
    }
}

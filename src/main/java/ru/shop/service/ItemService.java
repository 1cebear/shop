package ru.shop.service;

import ru.shop.model.Item;
import ru.shop.util.exception.NotFoundException;

import java.util.List;

public interface ItemService {

    Item save(Item item, int categoryId);

    void delete(int id, int categoryId) throws NotFoundException;

    Item get(int id, int categoryId) throws NotFoundException;

    List<Item> getAll(int categoryId);

    void update(Item item, int categoryId);
}

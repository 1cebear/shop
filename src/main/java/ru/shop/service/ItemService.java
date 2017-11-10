package ru.shop.service;

import ru.shop.model.Item;
import ru.shop.util.exception.NotFoundException;

import java.util.List;

public interface ItemService {

    Item save(Item item);

    void delete(int id) throws NotFoundException;

    Item get(int id) throws NotFoundException;

    List<Item> getAll();

    void update(Item item);
}

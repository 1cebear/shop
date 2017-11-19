package ru.shop.repository;

import ru.shop.model.Item;

import java.util.List;

public interface ItemRepository {
    Item save(Item item, int categoryId);

    // false if not found
    boolean delete(int id, int categoryId);

    // null if not found
    Item get(int id, int categoryId);

    List<Item> getAll(int categoryId);
}

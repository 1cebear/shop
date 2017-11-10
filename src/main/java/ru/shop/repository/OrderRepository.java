package ru.shop.repository;

import ru.shop.model.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);

    // false if not found
    boolean delete(int id);

    // null if not found
    Order get(int id);

    List<Order> getAll();
}

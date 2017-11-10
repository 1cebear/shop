package ru.shop.repository;

import ru.shop.model.OrderRow;

import java.util.List;

public interface OrderRowRepository {
    OrderRow save(OrderRow orderRow, int orderId);

    // false if not found
    boolean delete(int id, int orderId);

    // null if not found
    OrderRow get(int id, int orderId);

    List<OrderRow> getAll(int orderId);
}

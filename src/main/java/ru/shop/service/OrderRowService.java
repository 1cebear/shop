package ru.shop.service;

import ru.shop.model.OrderRow;
import ru.shop.util.exception.NotFoundException;

import java.util.List;

public interface OrderRowService {

    OrderRow save(OrderRow orderRow, int orderId);

    void delete(int id, int orderId) throws NotFoundException;

    OrderRow get(int id, int orderId) throws NotFoundException;

    List<OrderRow> getAll(int orderId);

    void update(OrderRow orderRow, int orderId);
}

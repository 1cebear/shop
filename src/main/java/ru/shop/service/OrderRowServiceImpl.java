package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.shop.model.OrderRow;
import ru.shop.repository.OrderRowRepository;
import ru.shop.util.exception.NotFoundException;

import java.util.List;

import static ru.shop.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OrderRowServiceImpl implements OrderRowService {

    private final OrderRowRepository repository;

    public OrderRowServiceImpl(OrderRowRepository repository) {
        this.repository = repository;
    }

    public OrderRow save(OrderRow orderRow, int orderId) {
        Assert.notNull(orderRow, "orderrow must not be null");
        return repository.save(orderRow, orderId);
    }

    public void delete(int id, int orderId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, orderId), id);
    }

    public OrderRow get(int id, int orderId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, orderId), id);
    }

    public List<OrderRow> getAll(int orderId) {
        return repository.getAll(orderId);
    }

    public void update(OrderRow orderRow, int orderId) {
        Assert.notNull(orderRow, "orderrow must not be null");
        repository.save(orderRow, orderId);
    }
}

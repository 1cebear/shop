package ru.shop.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.shop.model.Order;
import ru.shop.repository.OrderRepository;
import ru.shop.util.EmailSender;
import ru.shop.util.exception.NotFoundException;

import java.util.List;

import static ru.shop.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    public Order save(Order order) {
        Assert.notNull(order, "order must not be null");
        Order saved = repository.save(order);
        EmailSender.send(saved.getEmail());
        return saved;
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Order get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Order> getAll() {
        return repository.getAll();
    }

    public void update(Order order) {
        Assert.notNull(order, "order must not be null");
        repository.save(order);
    }
}

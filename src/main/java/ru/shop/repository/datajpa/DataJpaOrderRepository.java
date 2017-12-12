package ru.shop.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.Order;
import ru.shop.repository.OrderRepository;

import java.util.List;

@Repository
public class DataJpaOrderRepository implements OrderRepository {

    @Autowired
    private CrudOrderRepository crudRepository;

    @Transactional
    public Order save(Order order) {
        return crudRepository.save(order);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public Order get(int id) {
        return crudRepository.findOne(id);
    }

    public List<Order> getAll() {
        return crudRepository.findAll();
    }
}

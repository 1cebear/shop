package ru.shop.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.OrderRow;
import ru.shop.repository.OrderRowRepository;

import java.util.List;

@Repository
public class DataJpaOrderRowRepository implements OrderRowRepository {

    @Autowired
    private CrudOrderRowRepository crudOrderRowRepository;

    @Autowired
    private CrudOrderRepository crudOrderRepository;

    @Transactional
    public OrderRow save(OrderRow orderRow, int orderId) {
        if (!orderRow.isNew() && get(orderRow.getId(), orderId) == null)
            return null;
        orderRow.setOrder(crudOrderRepository.findOne(orderId));
        return crudOrderRowRepository.save(orderRow);
    }

    public boolean delete(int id, int orderId) {
        return crudOrderRowRepository.delete(id, orderId) != 0;
    }

    public OrderRow get(int id, int orderId) {
        OrderRow orderRow = crudOrderRowRepository.findOne(id);
        return orderRow != null && orderRow.getOrder().getId() == orderId ? orderRow : null;
    }

    public List<OrderRow> getAll(int orderId) {
        return crudOrderRowRepository.getAll(orderId);
    }
}

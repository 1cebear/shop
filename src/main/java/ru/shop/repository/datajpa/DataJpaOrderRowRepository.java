package ru.shop.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.shop.model.OrderRow;
import ru.shop.repository.OrderRowRepository;

import java.util.List;

@Repository
public class DataJpaOrderRowRepository implements OrderRowRepository {

    @Autowired
    private CrudOrderRowRepository crudOrderRowRepositoryy;

    @Autowired
    private CrudOrderRepository crudOrderRepository;

    public OrderRow save(OrderRow orderRow, int orderId) {
        if (!orderRow.isNew() && get(orderRow.getId(), orderId) == null)
            return null;
        orderRow.setOrder(crudOrderRepository.getOne(orderId));
        return crudOrderRowRepositoryy.save(orderRow);
    }

    public boolean delete(int id, int orderId) {
        return crudOrderRowRepositoryy.delete(id, orderId) != 0;
    }

    public OrderRow get(int id, int orderId) {
        OrderRow orderRow = crudOrderRowRepositoryy.findOne(id);
        return orderRow != null && orderRow.getOrder().getId() == orderId ? orderRow : null;
    }

    public List<OrderRow> getAll(int orderId) {
        return crudOrderRowRepositoryy.getAll(orderId);
    }
}

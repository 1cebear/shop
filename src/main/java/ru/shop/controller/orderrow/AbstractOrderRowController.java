package ru.shop.controller.orderrow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shop.model.OrderRow;
import ru.shop.service.OrderRowService;

import java.util.List;

import static ru.shop.util.ValidationUtil.checkIdConsistent;
import static ru.shop.util.ValidationUtil.checkNew;

public class AbstractOrderRowController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderRowService service;

    public List<OrderRow> getAll(int orderId) {
        log.info("getAll");
        return service.getAll(orderId);
    }

    public OrderRow get(int id, int orderId) {
        log.info("get {}", id);
        return service.get(id, orderId);
    }

    public OrderRow create(OrderRow orderRow, int orderId) {
        log.info("create {}", orderRow);
        checkNew(orderRow);
        return service.save(orderRow, orderId);
    }

    public void delete(int id, int orderId) {
        log.info("delete {}", id);
        service.delete(id, orderId);
    }

    public void update(OrderRow orderRow, int id, int orderId) {
        log.info("update {}", orderRow);
        checkIdConsistent(orderRow, id);
        service.update(orderRow, orderId);
    }
}

package ru.shop.repository.datajpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.OrderRow;

import java.util.List;

public interface CrudOrderRowRepository extends JpaRepository<OrderRow, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM OrderRow o WHERE o.id=:id AND o.order.id=:orderId")
    int delete(@Param("id") int id, @Param("orderId") int orderId);


    @Transactional
    OrderRow save(OrderRow order);


    @Query("SELECT o FROM OrderRow o WHERE o.order.id=:orderId")
    List<OrderRow> getAll(@Param("orderId") int orderId);
}

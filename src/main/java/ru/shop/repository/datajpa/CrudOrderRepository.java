package ru.shop.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.Order;

import java.util.List;


@Transactional(readOnly = true)
public interface CrudOrderRepository extends JpaRepository<Order, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.id=:id")
    int delete(@Param("id") int id);


    @Transactional
    Order save(Order order);

    @Query("SELECT o FROM Order o where o.id=:itemId")
    Order findOne(@Param("itemId") Integer id);


    List<Order> findAll(Sort sort);
}

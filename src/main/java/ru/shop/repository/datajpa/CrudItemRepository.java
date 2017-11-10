package ru.shop.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.Item;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudItemRepository extends JpaRepository<Item, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Item i WHERE i.id=:id")
    int delete(@Param("id") int id);


    @Transactional
    Item save(Item item);


    Item findOne(Integer id);


    List<Item> findAll(Sort sort);

}

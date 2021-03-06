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
    @Query("DELETE FROM Item i WHERE i.id=:id AND i.category.id=:categoryId")
    int delete(@Param("id") int id, @Param("categoryId") int categoryId);


    @Transactional
    Item save(Item item);

    @Query("SELECT i FROM Item i WHERE i.category.id=:categoryId")
    List<Item> getAll(@Param("categoryId") int categoryId);

}

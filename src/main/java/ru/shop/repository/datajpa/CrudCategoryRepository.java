package ru.shop.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.model.Category;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudCategoryRepository extends JpaRepository<Category, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Order o WHERE o.id=:id")
    int delete(@Param("id") int id);


    @Transactional
    Category save(Category category);

    @Query("SELECT c FROM Category c where c.id=:categoryId")
    Category findOne(@Param("categoryId") Integer id);


    List<Category> findAll(Sort sort);
}

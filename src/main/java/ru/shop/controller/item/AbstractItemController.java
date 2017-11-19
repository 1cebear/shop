package ru.shop.controller.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.shop.model.Item;
import ru.shop.service.ItemService;

import java.util.List;

import static ru.shop.util.ValidationUtil.checkIdConsistent;
import static ru.shop.util.ValidationUtil.checkNew;

public class AbstractItemController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ItemService service;

    public List<Item> getAll(int categoryId) {
        log.info("getAll");
        return service.getAll(categoryId);
    }

    public Item get(int id, int categoryId) {
        log.info("get {}", id);
        return service.get(id, categoryId);
    }

    public Item create(Item item, int categoryId) {
        log.info("create {}", item);
        checkNew(item);
        return service.save(item, categoryId);
    }

    public void delete(int id, int categoryId) {
        log.info("delete {}", id);
        service.delete(id, categoryId);
    }

    public void update(Item item, int id, int categoryId) {
        log.info("update {}", item);
        checkIdConsistent(item, id);
        service.update(item, categoryId);
    }
}

package ru.shop.controller.item;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.shop.model.Item;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ItemRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemRestController extends AbstractItemController {

    static final String REST_URL = "/rest/items";

    @Override
    @GetMapping
    public List<Item> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Item get(@PathVariable("id") int id) {
        return super.get(id);
    }


    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Item item, @PathVariable("id") int id) {
        super.update(item, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> createWithLocation(@RequestBody Item item) {
        Item created = super.create(item);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}

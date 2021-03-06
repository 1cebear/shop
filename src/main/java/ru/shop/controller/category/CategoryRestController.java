package ru.shop.controller.category;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.shop.model.Category;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = CategoryRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryRestController extends AbstractCategoryContoller {

    static final String REST_URL = "/rest/categories";

    @Override
    @GetMapping
    public List<Category> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Category get(@PathVariable("id") int id) {
        return super.get(id);
    }


    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Category category, @PathVariable("id") int id) {
        super.update(category, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createWithLocation(@RequestBody Category category) {
        Category created = super.create(category);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}


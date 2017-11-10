package ru.shop.controller.orderrow;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.shop.model.OrderRow;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = OrderRowRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRowRestController extends AbstractOrderRowController {

    static final String REST_URL = "/rest/orders/{orderId}/rows";

    @Override
    @GetMapping
    public List<OrderRow> getAll(@PathVariable("orderId") int orderId) {
        return super.getAll(orderId);
    }

    @Override
    @GetMapping("/{id}")
    public OrderRow get(@PathVariable("id") int id, @PathVariable("orderId") int orderId) {
        return super.get(id, orderId);
    }


    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("orderId") int orderId) {
        super.delete(id, orderId);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody OrderRow orderRow, @PathVariable("id") int id, @PathVariable("orderId") int orderId) {
        super.update(orderRow, id, orderId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderRow> createWithLocation(@RequestBody OrderRow orderRow, @PathVariable("orderId") int orderId) {
        OrderRow created = super.create(orderRow, orderId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}

package ru.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "orderrows")
public class OrderRow extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "c_name")
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    private Order order;

    @Column(name = "quantity")
    @Range(min = 1)
    private int quantity;

    @Column(name = "price")
    @Range(min = 0)
    private double price;

    @Column(name = "sum")
    @Range(min = 0)
    private double sum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false)
    @JsonBackReference(value = "b_name")
    @NotNull
    private Item item;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public OrderRow(Integer id, Order order, int quantity, double price, double sum, Item item) {
        super(id);
        this.order = order;
        this.quantity = quantity;
        this.price = price;
        this.sum = sum;
        this.item = item;
    }

    public OrderRow() {

    }

}

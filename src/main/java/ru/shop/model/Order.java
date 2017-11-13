package ru.shop.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    @JsonManagedReference
    private List<OrderRow> orderRowList;

    @Column(name = "commentary", nullable = false)
    private String commentary;

    public List<OrderRow> getOrderRowList() {
        return orderRowList;
    }

    public void setOrderRowList(List<OrderRow> orderRowList) {
        this.orderRowList = orderRowList;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Order(Integer id, String commentary) {
        super(id);
        this.commentary = commentary;
    }

    public Order(Integer id, List<OrderRow> orderRowList, String commentary) {
        super(id);
        this.orderRowList = orderRowList;
        this.commentary = commentary;
    }

    public Order() {

    }
}

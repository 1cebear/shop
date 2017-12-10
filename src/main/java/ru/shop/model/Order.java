package ru.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    @JsonManagedReference(value = "c_name")
    private List<OrderRow> orderRowList;

    @Column(name = "commentary", nullable = false)
    private String commentary;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "d_name")
    private User user;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "email", nullable = false)
    @NotNull
    private String email;

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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Order() {

    }

    public Order(Integer id, List<OrderRow> orderRowList, String commentary, User user, String name, String email) {
        super(id);
        this.orderRowList = orderRowList;
        this.commentary = commentary;
        this.user = user;
        this.name = name;
        this.email = email;
    }

    public Order(Integer id, String name, String email) {
        super(id);
        this.name = name;
        this.email = email;
    }
}

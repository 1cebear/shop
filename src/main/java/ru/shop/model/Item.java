package ru.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item extends BaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "price", nullable = false)
    @Range(min = 0)
    private double price;

    @Column(name = "description", nullable = false)
    @NotBlank
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    @JsonManagedReference(value = "b_name")
    private Set<OrderRow> orderRowSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference(value = "a_name")
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Item(Integer id, String name, double price, String description, Category category) {
        super(id);
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Item() {

    }
}

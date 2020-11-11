package se.sda.web.demo.inventories;

import se.sda.web.demo.products.Product;

import javax.persistence.*;
import java.util.List;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    @ManyToMany(mappedBy = "inventories")
    private List<Product> products;


    public Inventory() {
    }

    public Inventory(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

package com.samuel.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    private String barcode;
    private String name;
    private String description;
    private float price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="barcode", referencedColumnName="barcode")
    @ToString.Exclude
    private List<ProductRelease> releases;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return barcode != null && Objects.equals(barcode, product.barcode);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

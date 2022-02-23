package com.samuel.repository.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "operations")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Operation implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private OperationType operationType;

    @ManyToOne
    private ProductRelease productRelease;
    private long quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Operation operation = (Operation) o;
        return Objects.equals(id, operation.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

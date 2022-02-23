package com.samuel.repository;

import com.samuel.repository.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Customer, String> {

}

package com.lahssini.tp6ebankingbackend.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lahssini.tp6ebankingbackend.entities.Customer;
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    List<Customer>findByNameContains(String keyword);
}

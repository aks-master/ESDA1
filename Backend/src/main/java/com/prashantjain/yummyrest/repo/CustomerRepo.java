package com.prashantjain.yummyrest.repo;

import com.prashantjain.yummyrest.entity.Customer;
import com.prashantjain.yummyrest.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmailAndPassword(String email, String password);

    @Query("SELECT p FROM product p WHERE p.price BETWEEN :p1 AND :p2 ORDER BY p.price DESC")
    List<product> findTop2ByPriceRange(@Param("p1") int price1, @Param("p2") int price2);

}

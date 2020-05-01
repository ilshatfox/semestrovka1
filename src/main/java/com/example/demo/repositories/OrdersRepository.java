package com.example.demo.repositories;

import com.example.demo.models.Order;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUser(User user);
    Optional<Order> findOrderByUserAndId(User user, Long id);
}


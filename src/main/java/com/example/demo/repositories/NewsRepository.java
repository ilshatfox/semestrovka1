package com.example.demo.repositories;

import com.example.demo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface NewsRepository extends JpaRepository<Event, Long> {
    Optional<Event> findTopByOrderByIdDesc();


}


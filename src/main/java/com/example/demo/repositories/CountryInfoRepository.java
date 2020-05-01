package com.example.demo.repositories;

import com.example.demo.models.CountryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Repository
public interface CountryInfoRepository extends JpaRepository<CountryInfo, Long> {
    Optional<CountryInfo> findByCountryName(String countryName);

    @Query(
            value = "SELECT * FROM country_info",
            nativeQuery = true)
    List<CountryInfo> findAllCountryInfo();
}


package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);
}

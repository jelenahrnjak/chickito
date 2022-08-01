package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByName(String name);
}

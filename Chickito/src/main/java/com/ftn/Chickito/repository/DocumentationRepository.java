package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

    Optional<Documentation> findByText(String text);
}

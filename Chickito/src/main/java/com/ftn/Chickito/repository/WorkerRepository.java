package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository  extends JpaRepository<Worker, Long> {
}

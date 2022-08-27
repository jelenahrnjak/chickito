package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

    List<VacationRequest> findByApprovedIsNull();
}

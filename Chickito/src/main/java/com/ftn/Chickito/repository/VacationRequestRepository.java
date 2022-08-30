package com.ftn.Chickito.repository;

import com.ftn.Chickito.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VacationRequestRepository extends JpaRepository<VacationRequest, Long> {

    List<VacationRequest> findByApprovedIsNull();

    @Query("select v from VacationRequest v where v.approved is null and v.requestReviewer.username = :username")
    List<VacationRequest> findAllByDirector(String username);
    @Query("select v from VacationRequest v where v.user.username = :username")
    List<VacationRequest> findAllByUser(String username);
}

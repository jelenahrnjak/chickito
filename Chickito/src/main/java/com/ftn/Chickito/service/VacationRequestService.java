package com.ftn.Chickito.service;

import com.ftn.Chickito.model.VacationRequest;

import java.time.LocalDate;
import java.util.List;

public interface VacationRequestService {

    boolean createVacationRequest(String username, LocalDate startDate, LocalDate endDate);
    boolean approveVacationRequest(String username, Long vacationRequestId);
    boolean rejectVacationRequest(String username, Long vacationRequestId, String reasonForRejection);

    List<VacationRequest> findAllByDirector(String username);
    List<VacationRequest> findAllByUser(String username);

    void delete(String currentUser, Long vacationId) throws Exception;
}

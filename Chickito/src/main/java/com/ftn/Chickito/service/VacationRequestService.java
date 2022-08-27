package com.ftn.Chickito.service;

import java.time.LocalDate;

public interface VacationRequestService {

    boolean createVacationRequest(String username, LocalDate startDate, LocalDate endDate);
    boolean approveVacationRequest(String username, Long vacationRequestId);
    boolean rejectVacationRequest(String username, Long vacationRequestId, String reasonForRejection);
}

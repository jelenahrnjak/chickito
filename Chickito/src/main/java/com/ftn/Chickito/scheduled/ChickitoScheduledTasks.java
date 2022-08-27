package com.ftn.Chickito.scheduled;

import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.repository.VacationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ChickitoScheduledTasks {

    private final UserRepository userRepository;
    private final VacationRequestRepository vacationRequestRepository;

    private static final String REQUEST_EXPIRED = "Request rejected because it was expired.";

    @Scheduled(cron = "0 0 5 1 1 *")
    public void addVacationDays() {
        userRepository.findAll().forEach(user -> {
            user.setAvailableVacationDays(user.getAvailableVacationDays() + 20);
            userRepository.save(user);
        });
    }

    @Scheduled(cron = "0 0 5 * * *")
    public void rejectExpiredVacationRequests() {
        vacationRequestRepository.findByApprovedIsNull().forEach(vacationRequest -> {
            if (vacationRequest.getRequestExpirationDate().isEqual(LocalDate.now()) || vacationRequest.getRequestExpirationDate().isBefore(LocalDate.now())) {
                vacationRequest.setApproved(false);
                vacationRequest.setReasonForRejection(REQUEST_EXPIRED);
                vacationRequestRepository.save(vacationRequest);
            }
        });
    }
}

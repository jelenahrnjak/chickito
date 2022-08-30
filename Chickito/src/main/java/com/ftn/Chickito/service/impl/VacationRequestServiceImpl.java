package com.ftn.Chickito.service.impl;

import com.ftn.Chickito.exception.DirectorNotSuperiorForUserException;
import com.ftn.Chickito.exception.NotEnoughAvailableDaysException;
import com.ftn.Chickito.model.RequestedDay;
import com.ftn.Chickito.model.User;
import com.ftn.Chickito.model.VacationDay;
import com.ftn.Chickito.model.VacationRequest;
import com.ftn.Chickito.repository.UserRepository;
import com.ftn.Chickito.repository.VacationRequestRepository;
import com.ftn.Chickito.service.UserService;
import com.ftn.Chickito.service.VacationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacationRequestServiceImpl implements VacationRequestService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final VacationRequestRepository vacationRequestRepository;

    private static final String DIRECTOR_ROLE = "DIRECTOR";

    @Override
    public boolean createVacationRequest(String username, LocalDate startDate, LocalDate endDate) {

        User user = userService.findByUsername(username);
        List<LocalDate> days = getVacationDaysFromRequest(startDate, endDate);
        verifyThatUserHaveEnoughVacationDays(user.getAvailableVacationDays(), days.size());

        VacationRequest newVacationRequest = VacationRequest.builder()
                .user(user)
                .dates(days.stream().map(day -> RequestedDay.builder().date(day).build()).collect(Collectors.toList()))
                .requestExpirationDate(days.stream().min(Comparator.naturalOrder()).get())
                .approved(null)
                .requestReviewer(user.getRole().getName() == "DIRECTOR" ? user : user.getSector().getCompany().getDirector())
                .build();

        vacationRequestRepository.save(newVacationRequest);

        if (user.getRole().getName().equals(DIRECTOR_ROLE)) {
            approveVacationRequest(username, newVacationRequest.getId());
        }

        return true;
    }

    @Override
    public boolean approveVacationRequest(String username, Long vacationRequestId) {

        VacationRequest vacationRequest = getVacationRequest(vacationRequestId);
        User user = vacationRequest.getUser();
        User director = userService.findByUsername(username);

        if (!user.getId().equals(director.getId())) {
            verifyThatDirectorIsSuperiorForUser(user, director);
            verifyThatUserHaveEnoughVacationDays(user.getAvailableVacationDays(), vacationRequest.getDates().size());
        }

        vacationRequest.setRequestReviewer(director);
        vacationRequest.setApproved(true);
        vacationRequestRepository.save(vacationRequest);

        List<VacationDay> vacationDays = vacationRequest.getDates().stream().map(day -> VacationDay.builder().user(user).date(day.getDate())
                .build()).collect(Collectors.toList());

        user.getVacationDays().addAll(vacationDays);
        user.setAvailableVacationDays(user.getAvailableVacationDays() - vacationDays.size());
        userRepository.save(user);

        return true;
    }

    @Override
    public boolean rejectVacationRequest(String username, Long vacationRequestId, String reasonForRejection) {

        VacationRequest vacationRequest = getVacationRequest(vacationRequestId);
        User user = vacationRequest.getUser();
        User director = userService.findByUsername(username);

        verifyThatDirectorIsSuperiorForUser(user, director);

        vacationRequest.setRequestReviewer(director);
        vacationRequest.setApproved(false);
        vacationRequest.setReasonForRejection(reasonForRejection);
        vacationRequestRepository.save(vacationRequest);

        return true;
    }

    @Override
    public List<VacationRequest> findAllByDirector(String username) {
        LocalDate today = LocalDate.now();
        List<VacationRequest> requests = new ArrayList<>();
        vacationRequestRepository.findAllByDirector(username).forEach(vacationRequest -> {
            if(today.isBefore(vacationRequest.getRequestExpirationDate())){
            }
            requests.add(vacationRequest);
        });
        return vacationRequestRepository.findAllByDirector(username);
    }

    @Override
    public List<VacationRequest> findAllByUser(String username) {
        return vacationRequestRepository.findAllByUser(username);
    }

    @Override
    public void delete(String currentUser, Long vacationId) throws Exception {
        VacationRequest vacationRequest = this.vacationRequestRepository.findById(vacationId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Ne postoji zahtev sa id-jem = %s.", vacationId)));

        if(!vacationRequest.getUser().getUsername().equals(currentUser)){
            throw new Exception(String.format("Nije moguće obrisati zahtev jer niste autor."));
        }

        if(vacationRequest.getApproved() != null){
            if(vacationRequest.getApproved() ==false){
                throw new Exception(String.format("Nije moguće obrisati zahtev jer je odbijen."));
            }else{
                throw new Exception(String.format("Nije moguće obrisati zahtev jer je prihvaćen. Kontaktirajte direktora."));
            }
        }

        this.vacationRequestRepository.delete(vacationRequest);
    }

    private List<LocalDate> getVacationDaysFromRequest(LocalDate startDate, LocalDate endDate) {

        List<LocalDate> vacationDays = new ArrayList<>();

        for (LocalDate date = startDate; date.isBefore(endDate) || date.isEqual(endDate); date = date.plusDays(1L)) {
            vacationDays.add(date);
        }

        vacationDays.removeIf(day -> DayOfWeek.SATURDAY == day.getDayOfWeek() || DayOfWeek.SUNDAY == day.getDayOfWeek());

        return vacationDays;
    }

    private void verifyThatUserHaveEnoughVacationDays(int availableVacationDays, int numberOfRequestedDays) {
        if (availableVacationDays < numberOfRequestedDays) {
            throw new NotEnoughAvailableDaysException(availableVacationDays, numberOfRequestedDays);
        }
    }

    private VacationRequest getVacationRequest(Long vacationRequestId) {
        return vacationRequestRepository.findById(vacationRequestId)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Vacation request with id = %s doesn't exist.", vacationRequestId)));
    }

    private void verifyThatDirectorIsSuperiorForUser(User user, User director) {
        if (!user.getSector().getCompany().getDirector().getId().equals(director.getId())) {
            throw new DirectorNotSuperiorForUserException();
        }
    }
}


package com.ftn.Chickito.model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vacation_requests")
public class VacationRequest {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private List<RequestedDay> dates;

    private LocalDate requestExpirationDate;

    private Boolean approved;

    @ManyToOne
    @JoinColumn(name = "request_reviewer_id")
    private User requestReviewer;

    private String reasonForRejection;
}

package com.ftn.Chickito.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="machineMaintenances")
public class MachineMaintenance {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User author;

    @OneToMany(mappedBy = "machineMaintenance", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<MachineMaintenanceItem> items = new HashSet<>();

    private LocalDate startDate;

    private LocalDate endDate;

}

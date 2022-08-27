package com.ftn.Chickito.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "machine_maintenance_items")
public class MachineMaintenanceItem {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private MachineMaintenance machineMaintenance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Machine machine;

    @Column(nullable = false)
    private String plan;
}

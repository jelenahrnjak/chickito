package com.ftn.Chickito.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="workers_on_machines")
public class WorkerOnMachine {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User worker;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Machine machine;

    @Column(nullable = false)
    private boolean mainWorker;
}

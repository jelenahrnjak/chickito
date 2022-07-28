package com.ftn.Chickito.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="workers_on_machines")
public class WorkerOnMachine {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Worker worker;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Machine machine;

    @Column(nullable = false)
    private boolean mainWorker;
}

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
@Table(name="reports")
public class MachineReport {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Leader author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Sector sector;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Machine machine;

}

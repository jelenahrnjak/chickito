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
@Table(name = "buildings")
public class Building {


    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Column(nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(nullable = false)
    private Company company;

    @Column(nullable = false)
    private boolean headOffice = false;
}

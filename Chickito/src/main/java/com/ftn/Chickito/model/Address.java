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
@Table(name = "addresses")
public class Address {


    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String street;

    @Column(unique = true, nullable = false)
    private String number;

    @Column(unique = true, nullable = false)
    private String longitude;

    @Column(unique = true, nullable = false)
    private String latitude;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Column(nullable = false)
    private City city;

}

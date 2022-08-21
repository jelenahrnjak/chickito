package com.ftn.Chickito.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private User reviewer;

    @Column
    private Boolean approved;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Machine> machines = new HashSet<>();
}

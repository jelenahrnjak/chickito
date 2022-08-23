package com.ftn.Chickito.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="machines")
public class Machine {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column( unique = true)
    private String serialNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Sector sector;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Documentation documentation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Order order;

    @Column(nullable = false)
    private boolean active = false;

    @Column(nullable = false)
    private Integer quantity;

    private Double price;
}

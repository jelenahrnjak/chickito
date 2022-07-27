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
@Table(name="suggestions")
public class Suggestion {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Column(nullable = false)
    private Worker worker;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Leader author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Director reviewer;

    @Column(nullable = false)
    private boolean approved = false;

    @Column(nullable = false)
    private String text;
}

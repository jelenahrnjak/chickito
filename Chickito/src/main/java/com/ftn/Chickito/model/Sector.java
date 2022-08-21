package com.ftn.Chickito.model;

import com.ftn.Chickito.model.enums.SectorType;
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
@Table(name = "sectors")
public class Sector {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private SectorType type;

    @OneToOne
    private User leader;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private  Company company;

    @Column(nullable = false)
    private boolean deleted;

}

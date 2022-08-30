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
@Table(name="documentations")
public class Documentation {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String workInstructions;
    private String washingInstructions;
    private String maintenanceInstructions;

    @OneToMany(mappedBy = "documentation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SparePart> spareParts = new HashSet<>();
}

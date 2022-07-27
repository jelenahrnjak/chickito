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
@Table(name="workers")
public class Worker extends User{

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @Column(nullable = false)
    private Sector sector;
}

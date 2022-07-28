package com.ftn.Chickito.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="directors")
public class Director extends User{

    public Director() {super();}
}

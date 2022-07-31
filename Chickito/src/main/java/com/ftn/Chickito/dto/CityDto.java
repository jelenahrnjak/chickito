package com.ftn.Chickito.dto;

import com.ftn.Chickito.model.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CityDto {

    private String id;
    private String name;
    private String postalCode;
    private String country;
}

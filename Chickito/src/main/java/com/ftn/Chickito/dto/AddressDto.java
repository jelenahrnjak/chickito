package com.ftn.Chickito.dto;

import com.ftn.Chickito.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDto {

    private String id;
    private String street;
    private String number;
    private String longitude;
    private String latitude;
    private CityDto city;

}

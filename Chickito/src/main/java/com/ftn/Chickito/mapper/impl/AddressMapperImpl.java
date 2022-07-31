package com.ftn.Chickito.mapper.impl;

import com.ftn.Chickito.dto.AddressDto;
import com.ftn.Chickito.dto.CityDto;
import com.ftn.Chickito.mapper.AddressMapper;
import com.ftn.Chickito.model.Address;
import com.ftn.Chickito.model.City;
import com.ftn.Chickito.model.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressMapperImpl implements AddressMapper {
    @Override
    public AddressDto addressToAddressDto(Address address) {

        CityDto city = new CityDto();
        city.setName(address.getCity().getName());
        city.setPostalCode(address.getCity().getPostalCode());
        city.setCountry(address.getCity().getCountry().getName());

        AddressDto dto = new AddressDto();
        dto.setStreet(address.getStreet());
        dto.setNumber(address.getNumber());
        dto.setLatitude(address.getLatitude());
        dto.setLongitude(address.getLongitude());
        dto.setCity(city);

        return  dto;
    }

    @Override
    public Address addressDtoToAddress(AddressDto dto) {

        Country country = new Country();
        country.setName(dto.getCity().getCountry());

        City city = new City();
        city.setName(dto.getCity().getName());
        city.setPostalCode(dto.getCity().getPostalCode());
        city.setCountry(country);

        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setLatitude(dto.getLatitude());
        address.setLongitude(dto.getLongitude());
        address.setCity(city);

        return  address;
    }

    @Override
    public String getAddressString(Address a) {

        String street = a.getStreet() + " " + a.getNumber();
        String city = a.getCity().getName() + " " + a.getCity().getPostalCode();


        return street + ", " + city + ", " + a.getCity().getCountry().getName();
    }
}

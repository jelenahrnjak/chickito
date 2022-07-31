package com.ftn.Chickito.mapper;

import com.ftn.Chickito.dto.AddressDto;
import com.ftn.Chickito.model.Address;

public interface AddressMapper {

    AddressDto addressToAddressDto(Address address);
    Address addressDtoToAddress(AddressDto dto);
    String getAddressString(Address a);
}

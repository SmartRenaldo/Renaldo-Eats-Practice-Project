package com.renaldo.service;

import com.renaldo.pojo.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    Address save(Address address);

    Address setDefault(Address address);

    Optional<Address> findById(Long id);

    Optional<Address> findDefault();

    List<Address> list(Address address);
}

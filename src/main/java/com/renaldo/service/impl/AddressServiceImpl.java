package com.renaldo.service.impl;

import com.renaldo.pojo.Address;
import com.renaldo.pojo.Customer;
import com.renaldo.repositories.AddressRepository;
import com.renaldo.service.AddressService;
import com.renaldo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public Address save(Address address) {
        if (customerService.getCurrentCustomer().isPresent()) {
            Customer customer = customerService.getCurrentCustomer().get();
            address.setCustomer(customer);

            return addressRepository.save(address);
        }

        return null;
    }

    @Override
    @Transactional
    public Address setDefault(Address address) {
        if (customerService.getCurrentCustomer().isPresent()) {
            Customer customer = customerService.getCurrentCustomer().get();

            addressRepository.findAllByCustomer(customer).stream().peek(i ->
                    i.setIsDefault(0)).collect(Collectors.toList());
            Optional<Address> byId = addressRepository.findById(address.getId());

            if (byId.isPresent()) {
                Address address1 = byId.get();
                address1.setIsDefault(1);

                return address1;
            }
        }

        return null;
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Optional<Address> findDefault() {
        Optional<Customer> currentCustomer = customerService.getCurrentCustomer();

        if (currentCustomer.isPresent()) {
            return addressRepository.findDefault(currentCustomer.get());
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public List<Address> list(Address address) {
        if (customerService.getCurrentCustomer().isPresent()) {
            Customer currentCustomer = customerService.getCurrentCustomer().get();
            address.setCustomer(currentCustomer);

            Sort.TypedSort<Address> sort = Sort.sort(Address.class);
            Sort and = sort.by(Address::getIsDefault).descending().and(sort.by(Address::getDateModified));

            return addressRepository.findAll((Specification<Address>) (root, query, criteriaBuilder) -> {
                ArrayList<Predicate> predicates = new ArrayList<>();

                Path<Object> customer = root.get("customer");

                if (address.getCustomer() != null && address.getCustomer().getId() > -1) {
                    predicates.add(criteriaBuilder.equal(customer, address.getCustomer()));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }, and);
        }

        return null;
    }

    @Override
    @Transactional
    public Boolean update(Address address) {
        Optional<Address> byId = addressRepository.findById(address.getId());

        if (!byId.isPresent()) {
            return false;
        }

        Address addressById = byId.get();
        addressById.setGender(address.getGender());
        addressById.setDetail(address.getDetail());
        addressById.setName(address.getName());
        addressById.setPhone(address.getPhone());
        addressById.setLabel(address.getLabel());
        addressById.setDateModified(new Date());

        return true;

    }
}

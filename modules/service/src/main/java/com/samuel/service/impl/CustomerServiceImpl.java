package com.samuel.service.impl;

import com.samuel.repository.CustomerRepository;
import com.samuel.repository.model.Customer;
import com.samuel.service.CustomerService;
import com.samuel.service.dto.PersonDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CustomerRepository customerRepository;

    @CacheEvict(value = CACHE_NAME, key = "#cpf")
    @Override
    public PersonDto save(final PersonDto person) {
        return convertToDto(customerRepository.save(convertFromDto(person)));
    }

    @CacheEvict(value = CACHE_NAME, key = "#cpf")
    @Override
    public void remove(String cpf) {
        customerRepository.deleteById(cpf);
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonDto> list() {
        List<Customer> customers = customerRepository.findAll();
        if (customers == null) {
            return Collections.emptyList();
        }

        return customers.stream().map(this::convertToDto).toList();
    }

    @Cacheable(CACHE_NAME)
    @Transactional(readOnly = true)
    @Override
    public PersonDto get(String cpf) {
        return customerRepository.findById(cpf)
                .map(this::convertToDto)
                .get();
    }

    private PersonDto convertToDto(final Customer obj) {
        if (obj == null) {
            return null;
        }

        return modelMapper.map(obj, PersonDto.class);
    }

    private Customer convertFromDto(final PersonDto dto) {
        if (dto == null) {
            return null;
        }

        return modelMapper.map(dto, Customer.class);
    }
}

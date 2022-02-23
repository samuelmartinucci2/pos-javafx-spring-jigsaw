package com.samuel.service.impl;

import com.samuel.repository.EmployeeRepository;
import com.samuel.repository.model.Employee;
import com.samuel.service.EmployeeService;
import com.samuel.service.dto.PersonDto;
import com.samuel.service.util.LoginRoles;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private EmployeeRepository employeeRepository;

    @CacheEvict(value = CACHE_NAME, key = "#cpf")
    @Secured(LoginRoles.ADMIN)
    @Override
    public PersonDto save(final PersonDto person) {
        return convertToDto(employeeRepository.save(convertFromDto(person)));
    }

    @CacheEvict(value = CACHE_NAME, key = "#cpf")
    @Secured(LoginRoles.ADMIN)
    @Override
    public void remove(String cpf) {
        employeeRepository.deleteById(cpf);
    }

    @Transactional(readOnly = true)
    @Secured(LoginRoles.ADMIN)
    @Override
    public List<PersonDto> list() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees == null) {
            return Collections.emptyList();
        }

        return employees.stream().map(this::convertToDto).toList();
    }

    @Cacheable(CACHE_NAME)
    @Transactional(readOnly = true)
    @Override
    public PersonDto get(String cpf) {
        return employeeRepository.findById(cpf)
                .map(this::convertToDto)
                .get();
    }

    private PersonDto convertToDto(final Employee obj) {
        if (obj == null) {
            return null;
        }

        return modelMapper.map(obj, PersonDto.class);
    }

    private Employee convertFromDto(final PersonDto dto) {
        if (dto == null) {
            return null;
        }

        return modelMapper.map(dto, Employee.class);
    }
}

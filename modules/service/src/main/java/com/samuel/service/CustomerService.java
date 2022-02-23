package com.samuel.service;

import com.samuel.service.dto.PersonDto;

import java.util.List;

public interface CustomerService {

    String CACHE_NAME = "customer";

    PersonDto save(PersonDto person);

    void remove(String cpf);

    List<PersonDto> list();

    PersonDto get(String cpf);
}

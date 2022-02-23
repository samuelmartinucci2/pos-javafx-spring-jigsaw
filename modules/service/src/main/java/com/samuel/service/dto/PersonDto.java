package com.samuel.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@lombok.Builder
public class PersonDto implements Serializable {
    private String cpf;
    private String name;
    private String email;
    private String address;
    private String postalCode;
    private String password;
    private boolean employee;
}

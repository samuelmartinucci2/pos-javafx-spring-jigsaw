package com.samuel.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class TransactionDto implements Serializable {
    private long id;
    private List<OperationDto> operations;
    private PersonDto employee;
    private float discount;
}

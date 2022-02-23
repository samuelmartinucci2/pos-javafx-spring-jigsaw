package com.samuel.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class CashRegisterDto implements Serializable {
    private long id;
    private float funds;
    private Date startTime;
    private Date endTime;
    private List<TransactionDto> transactions;
}

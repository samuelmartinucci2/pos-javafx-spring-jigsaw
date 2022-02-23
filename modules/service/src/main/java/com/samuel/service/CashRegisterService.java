package com.samuel.service;

import com.samuel.service.dto.CashRegisterDto;
import com.samuel.service.dto.TransactionDto;

public interface CashRegisterService {

    CashRegisterDto open();
    CashRegisterDto close();
    void addTransaction(TransactionDto transaction);
}

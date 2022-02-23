package com.samuel.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OperationDto implements Serializable {
    private long id;
    private OperationType operationType;
    private ProductDto productRelease;
    private long quantity;
}

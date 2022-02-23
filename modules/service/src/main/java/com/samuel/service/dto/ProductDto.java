package com.samuel.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductDto implements Serializable {
    private String barcode;
    private String name;
    private String description;
    private float price;
    private String release;
}

package com.samuel.repository.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class ProductReleaseKey implements Serializable {
    private String barcode;
    private String version;
}

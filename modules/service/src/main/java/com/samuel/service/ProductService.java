package com.samuel.service;

import com.samuel.repository.model.Product;
import com.samuel.service.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto add(ProductDto product);
    ProductDto update(ProductDto product);
    ProductDto remove(String barcode);
    ProductDto removeRelease(String barcode, String release);
    List<Product> list();
    Product get();
}

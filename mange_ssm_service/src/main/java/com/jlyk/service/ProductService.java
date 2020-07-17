package com.jlyk.service;

import com.jlyk.domian.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws Exception;

    void saveProduct(Product product);

}

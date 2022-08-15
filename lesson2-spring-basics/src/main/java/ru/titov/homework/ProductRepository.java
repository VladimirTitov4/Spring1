package ru.titov.homework;

public interface ProductRepository {

    Product findProductById(Long id);

    void addProduct(Long id, Product product);

}

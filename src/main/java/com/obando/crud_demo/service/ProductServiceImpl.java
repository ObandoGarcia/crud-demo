package com.obando.crud_demo.service;

import com.obando.crud_demo.model.Product;
import com.obando.crud_demo.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productFromDatabase = productRepository.findById(id);

        productFromDatabase.ifPresent(p -> {
            productRepository.deleteById(id);
        });

        return productFromDatabase;
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productFromDatabase = productRepository.findById(id);

        if (productFromDatabase.isPresent()) {

            Product p = productFromDatabase.orElseThrow();
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());

            return Optional.of(productRepository.save(p));
        }

        return productFromDatabase;
    }
}

package com.obando.crud_demo.controller;

import com.obando.crud_demo.model.Product;
import com.obando.crud_demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping
    public List<Product> listAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> viewOneProduct(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);

        if (productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createOneProduct(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }

        Product newProduct = productService.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOneProduct(@PathVariable Long id, @Valid @RequestBody Product product, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }

        Optional<Product> productOptional = productService.update(id, product);

        if (productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOneProduct(@PathVariable Long id){
        Optional<Product> productOptional = productService.delete(id);

        if (productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }
}

package com.springapps.shop.controllers;


import com.springapps.shop.dtos.ProductRequestDTO;
import com.springapps.shop.entities.Product;
import com.springapps.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequestDTO productRequestDTO) {
     return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findByid(id));
    }
}

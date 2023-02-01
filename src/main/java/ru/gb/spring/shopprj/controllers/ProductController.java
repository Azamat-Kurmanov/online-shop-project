package ru.gb.spring.shopprj.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.spring.shopprj.dto.ProductDto;
import ru.gb.spring.shopprj.entities.Product;
import ru.gb.spring.shopprj.exceptions.AppError;
import ru.gb.spring.shopprj.exceptions.ResourceNotFoundException;
import ru.gb.spring.shopprj.services.ProductService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice())).collect(Collectors.toList());
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getProductById(@PathVariable Long id){
//        Optional<Product> product = productService.findById(id);
//        if(product.isPresent()){
//            ResponseEntity<AppError> err = new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Продукт не найден, id:" + id), HttpStatus.NOT_FOUND);
//            return err;
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id:" + id));
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
